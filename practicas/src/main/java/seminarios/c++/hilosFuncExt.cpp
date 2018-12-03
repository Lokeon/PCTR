#include <thread>
#include <iostream>


void hola() { std::cout << "Hola" << std::endl; }

int main(void)
{
    std::thread hilo1 (hola);
    std::thread hilo2 (hola);

    hilo1.join();
    hilo2.join();

    std::cout << "Soy el hilo padre" << std::endl;
}