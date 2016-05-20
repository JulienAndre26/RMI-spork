package collection.database;

import java.io.Serializable;
import java.rmi.RemoteException;

import collection.interfaces.DistantObjectInterface;

/**
 * The Class DistantObject which allows to access object with RMI.
 */
public class DistantObject implements Serializable, DistantObjectInterface {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5361228353280201599L;

	/** The object. */
	private Object object;

	/**
	 * Instantiates a new distant object.
	 * 
	 * @param o
	 *            the o
	 */
	public DistantObject(Object o) {
		object = o;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see collection.interfaces.DistantObjectInterface#getObject()
	 */
	public Object getObject() throws RemoteException {
		return object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return object.toString();
	}

}
