#include <chrono>
#include <atomic>
#include <thread>
#include <vector>
#include <mutex>
#include <iostream>

struct Contador
{
    std::mutex lock;
    std::atomic<int> cont;
    int contl = 0;

    void intLock()
    {
        lock.lock();
        contl++;
        lock.unlock();
    }

    void intAtom()
    {
        cont = cont + 1;
    }
};

int main(void)
{
    Contador contador;
    int numIter = 1000, numExp = 1;
    std::vector<std::thread> hilos;
    std::chrono::time_point<std::chrono::system_clock> ini, fin;
    std::chrono::duration<double> tiempo;

    while (numExp < 10)
    {
        ini = std::chrono::system_clock::now();

        for (int i = 0; i < numIter; ++i)
        {
            hilos.push_back(std::thread([&contador, &numExp]() {
                for (int i = 0; i < (numExp + 1) * 1000; ++i)
                {
                    contador.intLock();
                }
            }));
        }

        for (auto &elto : hilos)
        {
            elto.join();
        }

        fin = std::chrono::system_clock::now();
        hilos.clear();

        tiempo = fin - ini;

        std::cout << " El tiempo de mutex con " << numExp * 1000 << " es: "
                  << tiempo.count() << std::endl;

        ini = std::chrono::system_clock::now();

        for (int i = 0; i < numIter; ++i)
        {
            hilos.push_back(std::thread([&contador, &numExp]() {
                for (int i = 0; i < (numExp + 1) * 1000; ++i)
                {
                    contador.intAtom();
                }
            }));
        }

        for (auto &elto : hilos)
        {
            elto.join();
        }

        fin = std::chrono::system_clock::now();
        hilos.clear();

        tiempo = fin - ini;

        std::cout << " El tiempo de atomic con " << numExp * 1000 << " es: "
                  << tiempo.count() << std::endl;

        numExp++;
    }
}