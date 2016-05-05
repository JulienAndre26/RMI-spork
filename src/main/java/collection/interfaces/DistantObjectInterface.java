package collection.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface DistantObjectInterface extends Remote {
    Object getObject() throws RemoteException; 
}
