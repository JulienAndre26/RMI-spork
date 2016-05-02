package collection;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * The Class Data.
 */
public class Data implements Serializable, DataInterface {

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
    public Data(Object o)
    {
        object = o;
    }
    
    public Object getObject() throws RemoteException {
        return object;
    }

}
