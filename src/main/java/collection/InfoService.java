package collection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class InfoService extends UnicastRemoteObject implements InfoServiceInterface {
    
    private static final long serialVersionUID = -8307948654852338156L;

    protected InfoService() throws RemoteException
    {    }

    @Override
    public List<String> getLatestRegKey(int n) throws RemoteException
    {
        return Gateway.getLatestRegKey(n);
    }

    @Override
    public List<String> getLatestUsedKey(int n) throws RemoteException
    {
        return Gateway.getLatestUsedKey(n);
    }

    @Override
    public List<String> getMostUsedKey(int t) throws RemoteException
    {
        return Gateway.getMostUsedKey(t);
    }

}
