package collectionclient;

import java.io.Serializable;

/**
 * The Class TestObject.
 */
public class TestObject implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2729381153529213204L;

    /** The msg. */
    private String msg;

    /**
     * Instantiates a new test object.
     * 
     * @param msg
     *            the msg
     */
    public TestObject(String msg)
    {
        this.msg = msg;
    }

    /**
     * Gets the msg.
     * 
     * @return the msg
     */
    public String getMsg()
    {
        return msg;
    };

}
