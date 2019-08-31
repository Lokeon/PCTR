package ejerexam;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.Remote;

public interface iRMI extends Remote {

    public BigInteger factorial(int n) throws RemoteException;
}