package collection;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * The Class Data.
 */
public class Data implements Serializable, Remote {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5361228353280201599L;

    /** The object. */
    public Object object;

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

}
