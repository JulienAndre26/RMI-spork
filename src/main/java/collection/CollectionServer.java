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
    public boolean put(String key, Object o) throws InvalidNameException, NamingException,
            RemoteException
    {
        DistantObject serialObj = new DistantObject(o);
        if (Gateway.put(key, serialObj))
            context.rebind(new CompositeName(key), serialObj);
        else
            return false;

        return true;
    }

    public DistantObject get(String key) throws RemoteException
    {
        return (DistantObject) Gateway.get(key);
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
        System.out.print("> Launching Server");

        Hashtable<String, String> hashtableEnvironment = new Hashtable<String, String>();
        hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.rmi.registry.RegistryContextFactory");
        System.out.print(".");
        hashtableEnvironment.put(Context.PROVIDER_URL, url + ":" + port);
        System.out.print(".");
        CollectionServer.context = new InitialContext(hashtableEnvironment);
        System.out.println(".");
        CollectionServer.context.bind("CollectionServer", new CollectionServer());

        System.out.println("> Server ready");

    }

}
