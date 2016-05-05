package collection.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import javax.naming.NamingException;


/**
 * The Interface InfoServiceInterface.
 */
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
    List<String> getLatestRegKeys(int n) throws RemoteException;

    /**
     * Gets the latest used key.
     * 
     * @param n
     *            the n
     * @return the latest used key
     * @throws RemoteException
     *             the remote exception
     */
    List<String> getLatestUsedKeys(int n) throws RemoteException;

    /**
     * Gets the most used key.
     * 
     * @param t
     *            the t
     * @return the most used key
     * @throws RemoteException
     *             the remote exception
     */
    List<String> getMostUsedKeys(int t) throws RemoteException;

    /**
     * Gets the distant object list.
     *
     * @return the distant object list
     * @throws NamingException the naming exception
     */
    String getDistantObjectsList() throws NamingException, RemoteException;

}
