package client.entities;

import java.util.List;

import client.interfaces.ServiceInterface;

public class ServiceSum implements ServiceInterface {

    private static final long serialVersionUID = 4284595140873870496L;

    private String serviceName;
    
    public ServiceSum(String name){
        serviceName = name;
    }

    @Override
    public String getServiceName()
    {
        return serviceName;
    }

    @Override
    public Integer executeService(List<Object> args)
    {
        Integer res = 0;
        for (Object o : args){
            Integer i = 0;
            
            try {
                i = (Integer) o;
            } catch (ClassCastException e){
                System.out.println("Can't cast " + o + " into integer");
            }
            
            res += i;            
        }
        
        return res;
        
    }
    

}
