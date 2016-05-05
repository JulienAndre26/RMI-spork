package collection.database;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import collection.interfaces.InfoServiceInterface;

/**
 * The Class InfoService.
 */
public class InfoService extends UnicastRemoteObject implements InfoServiceInterface {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8307948654852338156L;
    private Context context;

    /**
     * Instantiates a new info service.
     * 
     * @throws RemoteException
     *             the remote exception
     */
    public InfoService(Context context) throws RemoteException
    {
        this.context = context;
    }
    
    @Override
    public String getDistantObjectsList() throws NamingException
    {
    	System.out.println("SERVER> InfoService: Get distant objects lists");
    	
        String res = "";
        
        NamingEnumeration<NameClassPair> list = context.list("");

        NameClassPair ncp;
        int i = -1;

        while (list.hasMoreElements())
        {
            ncp = list.nextElement();
            res += "\t" + ++i + ": " + ncp.getName() + "\n";
        }
        
        return res;
    }
    /*
     * (non-Javadoc)
     * @see collection.InfoServiceInterface#getLatestRegKey(int)
     */
    @Override
    public List<String> getLatestRegKeys(int n) throws RemoteException
    {
        System.out.println("SERVER> InfoService: Get last registered keys");
        return Gateway.getLatestRegKeys(n);
    }

    /*
     * (non-Javadoc)
     * @see collection.InfoServiceInterface#getLatestUsedKey(int)
     */
    @Override
    public List<String> getLatestUsedKeys(int n) throws RemoteException
    {
        System.out.println("SERVER> InfoService: Get last used keys");
        return Gateway.getLatestUsedKeys(n);
    }

    /*
     * (non-Javadoc)
     * @see collection.InfoServiceInterface#getMostUsedKey(int)
     */
    @Override
    public List<String> getMostUsedKeys(int t) throws RemoteException
    {
        System.out.println("SERVER> InfoService: Get most used keys");
        return Gateway.getMostUsedKeys(t);

    }

}
