#include <mutex>
#include <thread>
#include <iostream>
#include <condition_variable>
#include <vector>

struct Monitor
{

    Monitor() : numElto(0)
    {
    }

    void insertar(int indice, int value)
    {
        std::unique_lock<std::mutex> cerrojo(lock);
        if (numElto == 100)
        {
            std::cout << "Esta lleno " << std::endl;
            lleno.wait(cerrojo);
        }
        std::cout << "Produciendo..." << std::endl;
        elementos[indice] = value;
        numElto++;
        vacio.notify_all();
    }

    int extraer(int indice)
    {
        std::unique_lock<std::mutex> cerrojo(lock);
        if (numElto == 0)
        {
            std::cout << "Esta vacio" << std::endl;
            vacio.wait(cerrojo);
        }

        int elto = elementos[indice];
        numElto--;
        lleno.notify_all();

        return elto;
    }

    int numElto;
    std::condition_variable lleno, vacio;
    std::mutex lock;
    int elementos[100];
};

int main(void)
{
    Monitor mon;

    int indice = 0, value;
    std::vector<std::thread> prod;
    std::vector<std::thread> cons;

    for (int i = 0; i < 1000; ++i)
    {
        prod.push_back(std::thread([&mon, &indice, &value]() {
            mon.insertar(indice, value);
        }));

        cons.push_back(std::thread([&mon, &indice]() {
            mon.extraer(indice);
        }));

        indice = (indice + 1) % 100;
    }

    // for (auto &elto : prod)
    // {
    //     elto.join();
    // }

    // for (auto &elto : cons)
    // {
    //     elto.join();
    // }
    for (int i = 0; i < 1000; ++i)
    {
        prod[i].join();
        cons[i].join();
    }
}