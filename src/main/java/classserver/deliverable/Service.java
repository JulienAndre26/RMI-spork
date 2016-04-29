package classserver.deliverable;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Service extends Remote {
    
    String getInfos() throws RemoteException;

    
}
