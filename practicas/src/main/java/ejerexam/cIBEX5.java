package ejerexam;

import java.rmi.Naming;

public class cIBEX5 {
    public static void main(String[] args) throws Exception {

        iIBEX5 server = (iIBEX5) Naming.lookup("rmi:/localhost:2020/ibex");
        System.out.println("Conexion Realizada");
        server.verValor(1);
    }
}