package client.entities;

import client.interfaces.DataInterface;

/**
 * The Class DataPi.
 */
public class DataPi implements DataInterface {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8758674084723295777L;

    /** The data name. */
    private String dataName;

    /** The value. */
    private double value;

    /**
     * Instantiates a new data pi.
     */
    public DataPi()
    {
        dataName = "Pi";
        value = 3.14159265358979323846264338327950288419716939937510582;
    }

    /*
     * (non-Javadoc)
     * @see client.interfaces.DataInterface#getDataName()
     */
    @Override
    public String getDataName()
    {
        return dataName;
    }

    /*
     * (non-Javadoc)
     * @see client.interfaces.DataInterface#getValue()
     */
    @Override
    public Double getValue()
    {
        return value;
    }

}
