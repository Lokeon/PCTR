#include <chrono>
#include <atomic>
#include <mutex>
#include <iostream>
#include <vector>
#include <thread>

struct Contador
{
    int cont = 0;
    std::mutex lock;
    std::atomic<int> cont2 = 0;

    void incAtomic()
    {
        cont2++;
    }

    void incMutex()
    {
        lock.lock();
        cont++;
        lock.unlock();
    }
};

int main(void)
{
    Contador conta;
    int numIter = 1000, numExp = 1;
    std::vector<std::thread> hilos;
    std::chrono::time_point<std::chrono::system_clock> ini, fin;
    std::chrono::duration<double> tiempo;

    while (numExp < 10)
    {
        ini = std::chrono::system_clock::now();

        for (int i = 0; i < 10; ++i)
        {
            hilos.push_back(std::thread([&conta, &numExp]() {
                for (int j = 0; j < (numExp + 1) * 1000; ++j)
                {
                    conta.incMutex();
                }
            }

                                        ));
        }

        for (auto &elto : hilos)
        {
            elto.join();
        }

        fin = std::chrono::system_clock::now();

        tiempo = fin - ini;

        std::cout << "Para el experimiento mutex " << numExp * 1000 << "\n"
                  << tiempo.count();

        hilos.clear();

        ini = std::chrono::system_clock::now();

        for (int i = 0; i < 10; ++i)
        {
            hilos.push_back(std::thread([&conta, &numExp]() {
                for (int j = 0; j < (numExp + 1) * 1000; ++j)
                {
                    conta.incAtomic();
                }
            }

                                        ));
        }

        for (auto &elto : hilos)
        {
            elto.join();
        }

        fin = std::chrono::system_clock::now();
        hilos.clear();

        tiempo = fin - ini;

        std::cout << "Para el experimiento Atomic " << numExp * 1000 << "\n"
                  << tiempo.count();

        ++numExp;
    }
}