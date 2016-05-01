package collection;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The Interface CollectionService.
 */
public interface CollectionServerInterface extends Remote {

    InfoServiceInterface getInfoService() throws RemoteException;
}
