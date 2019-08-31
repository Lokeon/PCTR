#include <thread>
#include <mutex>
#include <vector>
#include <iostream>

using namespace std;

struct Contador
{
    Contador() : cont(0) {}

    void inc()
    {
        lock.lock();
        cont++;
        lock.unlock();
    }

    std::mutex lock;
    int cont;
};

int main(void)
{
    int N = 1000;
    Contador sum;
    std::vector<std::thread> hilos;
    for (int i = 0; i < N; ++i)
    {
        hilos.push_back(std::thread([&sum, &N]() {
            for (int j = 0; j < N; ++j)
            {
                sum.inc();
            }
        }));
    }

    for (auto &elto : hilos)
    {
        elto.join();
    }

    std::cout << "Valor final: " << sum.cont << std::endl;
}