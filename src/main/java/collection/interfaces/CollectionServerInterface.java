package collection.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.naming.InvalidNameException;
import javax.naming.NamingException;

import collection.database.DistantObject;

/**
 * The Interface CollectionServer which defines the collection server model.
 */
public interface CollectionServerInterface extends Remote {

	/**
	 * Gets the info service.
	 * 
	 * @return the info service
	 * @throws RemoteException
	 *             the remote exception
	 */
	InfoServiceInterface getInfoService() throws RemoteException;

	/**
	 * Put an object in the collection.
	 * 
	 * @param key
	 *            the key
	 * @param o
	 *            the o
	 * @return true, if successful
	 * @throws InvalidNameException
	 *             the invalid name exception
	 * @throws NamingException
	 *             the naming exception
	 * @throws RemoteException
	 *             the remote exception
	 */
	boolean put(String key, Object o) throws InvalidNameException,
			NamingException, RemoteException;

	/**
	 * Gets an object from the collection.
	 * 
	 * @param key
	 *            the key
	 * @return the distant object
	 * @throws RemoteException
	 *             the remote exception
	 */
	DistantObject get(String key) throws RemoteException;
}
