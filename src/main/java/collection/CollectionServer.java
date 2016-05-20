package collection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;

import collection.database.DistantObject;
import collection.database.Gateway;
import collection.database.InfoService;
import collection.interfaces.CollectionServerInterface;
import collection.interfaces.InfoServiceInterface;

/**
 * The Class CollectionServer which allows to put objects in collection, get
 * objects and statistics about the collection.
 */
public class CollectionServer extends UnicastRemoteObject implements
		CollectionServerInterface {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8647662070129302333L;

	/** The info service. */
	private static InfoServiceInterface infoService;

	/** The context. */
	public static Context context;

	/**
	 * Instantiates a new collection server.
	 * 
	 * @throws NamingException
	 *             the naming exception
	 * @throws RemoteException
	 *             the remote exception
	 */
	public CollectionServer() throws NamingException, RemoteException {
		// Init
		infoService = new InfoService(context);
	}

	/* (non-Javadoc)
	 * @see collection.interfaces.CollectionServerInterface#put(java.lang.String, java.lang.Object)
	 */
	public boolean put(String key, Object o) throws InvalidNameException,
			NamingException, RemoteException {
		DistantObject serialObj = new DistantObject(o);
		if (Gateway.put(key, serialObj))
			context.rebind(new CompositeName(key), serialObj);
		else
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see collection.CollectionServerInterface#get(java.lang.String)
	 */
	public DistantObject get(String key) throws RemoteException {
		return (DistantObject) Gateway.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see collection.CollectionServerInterface#getInfoService()
	 */
	@Override
	public InfoServiceInterface getInfoService() throws RemoteException {
		return infoService;
	}
}
