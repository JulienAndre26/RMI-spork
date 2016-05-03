package collectionclient.interfaces;

import java.io.Serializable;


public interface DataInterface extends Serializable {
    
    public String getDataName();
    
    public Object getValue();

}
