package collection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class InfoService extends UnicastRemoteObject implements InfoServiceInterface {
    
    private static final long serialVersionUID = -8307948654852338156L;

    protected InfoService() throws RemoteException
    {    }

    @Override
    public List<String> getLatestRegKeys(int n) throws RemoteException
    {
        return Gateway.getLatestRegKeys(n);
    }

    @Override
    public List<String> getLatestUsedKeys(int n) throws RemoteException
    {
        return Gateway.getLatestUsedKeys(n);
    }

    @Override
    public List<String> getMostUsedKeys(int t) throws RemoteException
    {
        return Gateway.getMostUsedKeys(t);
    }

}
