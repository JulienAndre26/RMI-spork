package classserver.deliverable;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Service extends Remote {
    
    Multiplicator createMultiplicator() throws RemoteException;
    
    Counter createCounter() throws RemoteException;

}
