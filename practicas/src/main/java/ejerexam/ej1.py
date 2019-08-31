from threading import Thread
from threading import Lock
import random
import multiprocessing
import concurrent.futures

contadorGlobal = 0
cerrojoGlobal = Lock()


class MiClase:
    def __init__(self):
        self.val = 0

    def incrementar(self):
        global cerrojoGlobal
        cerrojoGlobal.acquire()
        self.val += 1
        cerrojoGlobal.release()

    def getValue(self):
        return self.val


objetoClaseGlobal = MiClase()


def Threads():
    global objetoClaseGlobal
    numHilos = 1000
    numIter = 50
    hilos = []
    for i in range(numHilos):
        hiloNuevo = Thread(target=funcionThreads, args=(numIter,))
        hilos.append(hiloNuevo)

    for i in hilos:
        i.start()

    for i in hilos:
        i.join()

    print(objetoClaseGlobal.getValue())


def funcionThreads(numIter):
    global objetoClaseGlobal
    for i in range(numIter):
        objetoClaseGlobal.incrementar()


def EjecutoresFuturos():
    global contadorGlobal
    numHilos = multiprocessing.cpu_count()
    with concurrent.futures.ThreadPoolExecutor(max_workers=numHilos) as ejecutor:
        for i in range(numHilos):
            aleatorio = int(random.random() * 10000)
            ejecutor.submit(contarPares, aleatorio)
    print(contadorGlobal)

def contarPares(numero):
    global cerrojoGlobal
    global contadorGlobal

    for i in range(numero):
        if(numero % 2 == 0):
            cerrojoGlobal.acquire()
            contadorGlobal+=1
            cerrojoGlobal.release()

if __name__ == '__main__':
    Threads()
    EjecutoresFuturos()
