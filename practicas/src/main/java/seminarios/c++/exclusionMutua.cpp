#include <iostream>
#include <thread>
#include <vector>
#include <mutex>

struct Contador
{
    std::mutex mutex;
    int valor = 0;
    void incremento() { mutex.lock(); valor++; mutex.unlock(); }
    // std::mutex cerrojo;
    // std::lock_guard<std::mutex> guard(cerrojo) Gestión automática
};

int main()
{
    Contador cont;
    std::vector<std::thread> hilos; //Creamos un vector de hilos
    for (int i = 0; i < 10; ++i)
    {
        hilos.push_back(std::thread([&cont]() { //Se le pasa la
            //estructura como parámetro
            for (int j = 0; j < 100; ++j)
            {
                cont.incremento();
            }
        }));
    }
    for (auto &thread : hilos)
    {
        thread.join();
    }
    std::cout << cont.valor << std::endl;
   }