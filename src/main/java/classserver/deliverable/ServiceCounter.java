package classserver.deliverable;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServiceCounter extends UnicastRemoteObject implements Service {

    private static final long serialVersionUID = 8308946086252702044L;

    protected ServiceCounter() throws RemoteException
    {
        super();
    }

    @Override
    public String getInfos() throws RemoteException
    {
        return "Service delivering a counter.";
    }
    
    public Counter createCounter(){
        return new Counter();
    }

}
