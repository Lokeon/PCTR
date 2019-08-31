package ejerexam;

import java.rmi.Naming;

public class cHelion {

    public static void main(String[] args) {
        try {
            iHelion serv = (iHelion) Naming.lookup("//localhost:2020/helion");
            serv.iniHelion();
            serv.status();
            while (!serv.arminsticio()) {
                serv.ojiva();
                serv.status();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}