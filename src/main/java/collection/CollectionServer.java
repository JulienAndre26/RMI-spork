package collection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;

/**
 * The Class CollectionServer.
 */
public class CollectionServer extends UnicastRemoteObject implements CollectionServerInterface {

    private static final long serialVersionUID = -8647662070129302333L;

    private static InfoServiceInterface infoService;
    
    public static Context context;

    /**
     * Instantiates a new collection server.
     * 
     * @param url
     *            the url
     * @param port
     *            the port
     * @throws NamingException
     *             the naming exception
     */
    public CollectionServer() throws NamingException, RemoteException
    {
        // Init
        infoService = new InfoService();
    }

    /**
     * Put.
     * 
     * @param key
     *            the key
     * @param o
     *            the o
     * @return true, if successful
     * @throws InvalidNameException
     *             the invalid name exception
     * @throws NamingException
     *             the naming exception
     */
    public boolean put(String key, Object o) throws InvalidNameException, NamingException, RemoteException
    {
        Data serialObj = new Data(o);
        if (Gateway.put(key, o))
            context.rebind(new CompositeName(key), serialObj);
        else
            return false;

        return true;
    }
    
    @Override
    public InfoServiceInterface getInfoService() throws RemoteException
    {
        return infoService;
    }

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     * @throws NamingException
     *             the naming exception
     * @throws RemoteException
     */
    public static void main(String[] args) throws NamingException, RemoteException
    {
        String url = "rmi://localhost";
        int port = 8082;
        
        // Launching Server !
        System.out.println("Launching Server");

        Hashtable<String, String> hashtableEnvironment = new Hashtable<String, String>();
        hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.rmi.registry.RegistryContextFactory");
        hashtableEnvironment.put(Context.PROVIDER_URL, url + ":" + port);
        
        CollectionServer.context = new InitialContext(hashtableEnvironment);
        
        CollectionServer.context.bind("CollectionServer", new CollectionServer());
        
        
    }


}
