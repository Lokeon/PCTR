package ejerexam;

import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.server.*;
import java.util.*;

public class primesS extends UnicastRemoteObject implements primesI
{
    public primesS() throws RemoteException
    {
        super();
    }

    
    public int primesInRange(int iniRange, int finRange)
    {
        int cont = 0;

        for(int i = iniRange; i < finRange; ++i)
        {
            if(i >= 2)
            {
                int numDivis = 0 ;

                for(int j = 2 ; j < Math.round((float) i/2f); ++i)
                {
                    if(i % j == 0)
                    {
                        numDivis++;
                    }
                }
                if(numDivis == 0)
                {
                    cont++;
                }
            }
        }
        
        return cont;
    }


    public static void main(String[]args)
    {
        try {
            primesS server = new primesS();
            Naming.bind("//localhost:1099/servidor",server);
            System.out.println("Server Listo!!");
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}