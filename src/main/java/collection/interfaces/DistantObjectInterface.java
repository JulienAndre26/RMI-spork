package collection.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The Interface DistantObjectInterface which defines the Distant Object model.
 */
public interface DistantObjectInterface extends Remote {

	/**
	 * Gets the object.
	 * 
	 * @return the object
	 * @throws RemoteException
	 *             the remote exception
	 */
	Object getObject() throws RemoteException;
}
