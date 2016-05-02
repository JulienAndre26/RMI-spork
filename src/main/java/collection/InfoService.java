package collection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * The Class InfoService.
 */
public class InfoService extends UnicastRemoteObject implements InfoServiceInterface {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8307948654852338156L;

    /**
     * Instantiates a new info service.
     * 
     * @throws RemoteException
     *             the remote exception
     */
    protected InfoService() throws RemoteException
    {}

    /*
     * (non-Javadoc)
     * @see collection.InfoServiceInterface#getLatestRegKey(int)
     */
    @Override
    public List<String> getLatestRegKey(int n) throws RemoteException
    {
        System.out.println("GET LAST REGISTERED KEYS");
        return Gateway.getLatestRegKey(n);
    }

    /*
     * (non-Javadoc)
     * @see collection.InfoServiceInterface#getLatestUsedKey(int)
     */
    @Override
    public List<String> getLatestUsedKey(int n) throws RemoteException
    {
        System.out.println("GET LAST USED KEYS");
        return Gateway.getLatestUsedKey(n);
    }

    /*
     * (non-Javadoc)
     * @see collection.InfoServiceInterface#getMostUsedKey(int)
     */
    @Override
    public List<String> getMostUsedKey(int t) throws RemoteException
    {
        System.out.println("GET MOST USED KEYS");
        return Gateway.getMostUsedKey(t);
    }

}
