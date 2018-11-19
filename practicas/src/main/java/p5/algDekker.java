package p5;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kevin Lopez Cala
 */
public class algDekker {

    /* Number of processes currently in critical section */
    private static volatile int inCS = 0;
    /* Process p wants to enter critical section */
    private static volatile boolean wantp = false;
    /* Process q wants to enter critical section */
    private static volatile boolean wantq = false;
    /* Process r wants to enter critical section */
    private static volatile boolean wantr = false;
    /* Which processes turn is it */
    private static volatile int turn = 1;

    class P extends Thread {

        public void run() {
            while (true) {
                /* Non-critical section */
                wantp = true;
                while (wantq || wantr) {
                    if (turn == 2 || turn == 3) {
                        wantp = false;
                        while (turn == 2 || turn == 3)
                            Thread.yield();
                        wantp = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section1: " + inCS);
                inCS--;
                turn = 2;
                wantp = false;
            }
        }
    }

    class Q extends Thread {
        public void run() {
            while (true) {
                /* Non-critical section */
                wantq = true;
                while (wantr || wantp) {
                    if (turn == 1 || turn == 3) {
                        wantq = false;
                        while (turn == 1 || turn == 3)
                            Thread.yield();
                        wantq = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section2: " + inCS);
                inCS--;
                turn = 3;
                wantq = false;
            }
        }
    }

    class R extends Thread {

        public void run() {
            while (true) {
                /* Non-critical section */
                wantr = true;
                while (wantp || wantq) {
                    if (turn == 1 || turn == 2) {
                        wantr = false;
                        while (turn == 1 || turn == 2)
                            Thread.yield();
                        wantr = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section3: " + inCS);
                inCS--;
                turn = 1;
                wantr = false;
            }
        }

    }

    public algDekker() {
        Thread p = new P();
        Thread q = new Q();
        Thread r = new R();
        p.start();
        q.start();
        r.start();

    }

    public static void main(String[] args) {
        new algDekker();
    }
}