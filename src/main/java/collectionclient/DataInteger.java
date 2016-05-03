package collectionclient;

import collectionclient.interfaces.DataInterface;

/**
 * The Class TestObject.
 */
public class DataInteger implements DataInterface {

    private static final long serialVersionUID = -422685837211604429L;

    private String dataName;
    private int integer;
    
    public DataInteger(String name, int value){
        dataName = name;
        integer = value;
    }
    
    
    @Override
    public String getDataName()
    {
        return dataName;
    }

    @Override
    public Integer getValue()
    {
        return integer;
    }

}
