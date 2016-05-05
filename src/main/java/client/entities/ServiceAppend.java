package client.entities;

import java.util.ArrayList;
import java.util.List;

import client.interfaces.ServiceInterface;

public class ServiceAppend implements ServiceInterface {

    private static final long serialVersionUID = -780991566430964466L;

    private String serviceName;
    
    public ServiceAppend(String name){
        serviceName = name;
    }

    @Override
    public String getServiceName()
    {
        return serviceName;
    }

    @Override
    public List<Object> executeService(List<Object> args)
    {
        List<Object> res = new ArrayList<>();
        
        for (Object o : args){
            try {
                @SuppressWarnings("unchecked")
                List<Object> list = (List<Object>) o;
                
                for (Object o2 : list)
                    res.add(o2);
                
            } catch (ClassCastException e){
                System.out.println("Can't cast into List<Object>");
            }            
        }
        
        return res;
    }
    

}
