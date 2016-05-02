package collection;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface DataInterface extends Remote {
    Object getObject() throws RemoteException; 
}
