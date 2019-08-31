
package ejerexam;

import java.rmi.*;

public interface primeInterface extends Remote {

    public int primesInRange(int iniRange, int endRange) throws RemoteException;
}