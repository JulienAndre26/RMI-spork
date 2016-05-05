package client.interfaces;

import java.io.Serializable;
import java.util.List;


public interface ServiceInterface extends Serializable {
    
    public String getServiceName();
    
    public Object executeService(List<Object> args);

}
