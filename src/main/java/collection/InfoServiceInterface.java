package collection;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface InfoServiceInterface extends Remote {
    
    /**
     * Gets the latest reg key.
     * 
     * @param n
     *            the n
     * @return the latest reg key
     * @throws RemoteException
     *             the remote exception
     */
    List<String> getLatestRegKey(int n) throws RemoteException;

    /**
     * Gets the latest used key.
     * 
     * @param n
     *            the n
     * @return the latest used key
     * @throws RemoteException
     *             the remote exception
     */
    List<String> getLatestUsedKey(int n) throws RemoteException;

    /**
     * Gets the most used key.
     * 
     * @param t
     *            the t
     * @return the most used key
     * @throws RemoteException
     *             the remote exception
     */
    List<String> getMostUsedKey(int t) throws RemoteException;

}
