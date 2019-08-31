#include <mutex>
#include <thread>
#include <iostream>
#include <condition_variable>
#include <vector>

struct Monitor
{
    Monitor() : numEltos(0) {}

    void insertar(int indice, int value)
    {
        std::unique_lock<std::mutex> cerrojo(lock);

        if (numEltos == 100)
        {
            std::cout << "Lleno" << std::endl;
            lleno.wait(cerrojo);
        }
        std::cout << "Produciendo..." << std::endl;
        elementos[indice] = value;
        numEltos++;
        vacio.notify_all();
    }

    int extraer(int indice)
    {
        std::unique_lock<std::mutex> cerrojo(lock);

        if (numEltos == 0)
        {
            std::cout << "Vacio" << std::endl;
            vacio.wait(cerrojo);
        }

        int elto = elementos[indice];
        numEltos--;
        lleno.notify_all();

        return elto;
    }

    int numEltos;
    std::mutex lock;
    std::condition_variable vacio, lleno;
    int elementos[100];
};

int main(void)
{
    Monitor m;
    int indice = 0, value;
    std::vector<std::thread> productores, consumidores;

    for (int i = 0; i < 1000; ++i)
    {
        productores.push_back(std::thread([&m, &indice, &value]() {
            m.insertar(indice, value);
        }));

        consumidores.push_back(std::thread([&m, &indice]() {
            m.extraer(indice);
        }));

        indice = (indice + 1) % 100;
    }

    for (int i = 0; i < 1000; ++i)
    {
        productores[i].join();
        consumidores[i].join();
    }
}