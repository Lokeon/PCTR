#include <iostream>
#include <thread>

int main(void)
{
    std::thread hilo1([]() { std::cout << "Hola soy el hijo " << std::this_thread::get_id() << std::endl; });
    std::thread hilo2([]() { std::cout << "Hola soy el hijo "<< std::this_thread::get_id() << std::endl; });
    hilo1.join();
    hilo2.join();
    std::cout << "Soy el hilo padre" << std::endl;
}