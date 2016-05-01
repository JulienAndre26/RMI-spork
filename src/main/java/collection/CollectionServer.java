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

    /** The context. */
    private Context context;

    /** The hashtable environment. */
    private Hashtable<String, String> hashtableEnvironment;

    private static InfoServiceInterface infoService;

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
    public CollectionServer(String url, int port) throws NamingException, RemoteException
    {
        // Init
        infoService = new InfoService();
        
        hashtableEnvironment = new Hashtable<String, String>();
        hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.rmi.registry.RegistryContextFactory");
        hashtableEnvironment.put(Context.PROVIDER_URL, url + ":" + port);
        context = new InitialContext(hashtableEnvironment);
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
    public boolean put(String key, Object o) throws InvalidNameException, NamingException
    {
        if (Gateway.put(key, o))
            context.rebind(new CompositeName(key), o);
        else
            return false;

        return true;
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
        // Launching Server !
        CollectionServer cs = new CollectionServer("rmi://localhost", 8080);

        // Client sample
        cs.put("ReferenceHere", "ObjectHere");
    }

    @Override
    public InfoServiceInterface getInfoService() throws RemoteException
    {
        return infoService;
    }

}
