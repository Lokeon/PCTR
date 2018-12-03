#include <condition_variable>
#include <iostream>
#include <mutex>
#include <thread>
#include <vector>

using std::condition_variable;
using std::cout;
using std::endl;
using std::mutex;
using std::recursive_mutex;
using std::thread;
using std::unique_lock;

struct Marmita
{
  Marmita(int inicial = 10) { anguilas = inicial; }
  void comer()
  {
    unique_lock<mutex> ul_comer(lock_comer);
    if (anguilas == 0)
      cv_cocinero.notify_one();
    cv_comer.wait(ul_comer, [this]() { return anguilas != 0; });
    anguilas--;
    cout << "Comiendo" << endl;
  }
  void cocinar()
  {
    unique_lock<mutex> ul_cocina(lock_cocinero);
    cv_cocinero.wait(ul_cocina, [this]() { return (anguilas == 0); });
    cout << "Cocinando" << endl;
    anguilas = 10;
    cv_comer.notify_one();
  }
  mutex lock_cocinero;
  mutex lock_comer;
  condition_variable cv_cocinero;
  condition_variable cv_comer;
  int anguilas;
};

void vikingo(bool type, Marmita &m)
{

  while (true)
  {
    if (type == true)
    {
      m.cocinar();
    }
    else if (type == false)
    {
      m.comer();
    }
  }
}

int main(int argc, char const *argv[])
{
  Marmita m;
  thread d(vikingo, false, std::ref(m));
  thread b(vikingo, false, std::ref(m));
  thread c(vikingo, true, std::ref(m));
  thread a(vikingo, false, std::ref(m));

  a.join();
  b.join();
  c.join();
  d.join();
  return 0;
}