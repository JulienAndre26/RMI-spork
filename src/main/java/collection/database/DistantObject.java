package collection.database;

import java.io.Serializable;
import java.rmi.RemoteException;

import collection.interfaces.DistantObjectInterface;

/**
 * The Class Data.
 */
public class DistantObject implements Serializable, DistantObjectInterface {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5361228353280201599L;

    /** The object. */
    private Object object;

    /**
     * Instantiates a new data.
     * 
     * @param o
     *            the o
     */
    public DistantObject(Object o)
    {
        object = o;
    }
    
    public Object getObject() throws RemoteException {
        return object;
    }

	@Override
	public String toString() {
		return object.toString();
	}
    
    

}