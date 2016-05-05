package collection.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.naming.InvalidNameException;
import javax.naming.NamingException;

import collection.database.DistantObject;

/**
 * The Interface CollectionService.
 */
public interface CollectionServerInterface extends Remote {

    InfoServiceInterface getInfoService() throws RemoteException;
    
    boolean put(String key, Object o) throws InvalidNameException, NamingException, RemoteException;
    
    DistantObject get(String key) throws RemoteException;
}
