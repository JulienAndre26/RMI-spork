package client;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;

import client.interfaces.DataInterface;
import client.interfaces.ServiceInterface;

// TODO: ClassServer needs to work in order to delete this import
import collection.interfaces.CollectionServerInterface;

/**
 * The Class Client.
 */
public class Client {

    /** The name. */
    private String name;

    /** The url. */
    private String url;

    /** The port. */
    private int port;

    /** The hashtable environment. */
    private Hashtable<String, String> hashtableEnvironment;

    /** The context. */
    private Context context;

    /** The collection. */
    private CollectionServerInterface collection;

    /** The current service. */
    private ServiceInterface currentService;

    /**
     * Instantiates a new client.
     * 
     * @param name
     *            the name
     * @param url
     *            the url
     * @param port
     *            the port
     */
    public Client(String name, String url, int port)
    {
        this.name = name.toUpperCase();
        this.url = url;
        this.port = port;
    }

    /**
     * Connect.
     * 
     * @throws NamingException
     *             the naming exception
     */
    public void connect() throws NamingException
    {
        print("Connecting...");

        // String url = "rmi://localhost";
        // int port = 8082;

        hashtableEnvironment = new Hashtable<String, String>();
        hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.rmi.registry.RegistryContextFactory");
        hashtableEnvironment.put(Context.PROVIDER_URL, url + ":" + port);
        context = new InitialContext(hashtableEnvironment);

        collection = (CollectionServerInterface) context.lookup(new CompositeName(
                "CollectionServer"));

        print("Connected");
    }

    /**
     * Put distant object.
     * 
     * @param key
     *            the key
     * @param o
     *            the o
     * @throws InvalidNameException
     *             the invalid name exception
     * @throws RemoteException
     *             the remote exception
     * @throws NamingException
     *             the naming exception
     */
    public void putDistantObject(String key, Object o) throws InvalidNameException,
            RemoteException, NamingException
    {
        String type = isData(o) ? "data" : (isService(o) ? "service" : "(unknown type)");

        print("Put " + type + " with key \"" + key + "\"");
        collection.put(key, o);
    }

    /**
     * Checks if is data.
     * 
     * @param o
     *            the o
     * @return true, if is data
     */
    private boolean isData(Object o)
    {
        try
        {
            @SuppressWarnings("unused")
            DataInterface d = (DataInterface) o;
        }
        catch (ClassCastException e)
        {
            return false;
        }

        return true;
    }

    /**
     * Checks if is service.
     * 
     * @param o
     *            the o
     * @return true, if is service
     */
    private boolean isService(Object o)
    {
        try
        {
            @SuppressWarnings("unused")
            ServiceInterface s = (ServiceInterface) o;
        }
        catch (ClassCastException e)
        {
            return false;
        }

        return true;
    }

    /**
     * Gets the distant object.
     * 
     * @param key
     *            the key
     * @return the distant object
     * @throws RemoteException
     *             the remote exception
     */
    public void getDistantObject(String key) throws RemoteException
    {
        print("Get distant object with key \"" + key + "\"");
        Object o = collection.get(key).getObject();

        if (isService(o))
        {

            ServiceInterface service = (ServiceInterface) o;
            print("Distant object \""+ key + "\" is a service");

            print("Service name: " + service.getServiceName());

            this.currentService = service;
        }
        else if (isData(o))
        {
            DataInterface data = (DataInterface) o;
            print("Distant object \""+ key + "\" is a data");

            print("Data name: " + data.getDataName());
            print("Data value: " + data.getValue());
        }
        else
            print("Unknown object class");

    }

    /**
     * Execute current service.
     * 
     * @param args
     *            the args
     */
    public void executeCurrentService(List<Object> args)
    {
        print("Executing service with arg(s): " + args);
        print(this.currentService.executeService(args).toString());
    }

    /**
     * Gets the context list.
     * 
     * @return the context list
     * @throws NamingException
     *             the naming exception
     * @throws RemoteException 
     */
    public void getDistantObjectsList() throws NamingException, RemoteException
    {
        print("Get distant objects list");
        System.out.println(collection.getInfoService().getDistantObjectsList());
    }

    /**
     * Gets the last used keys.
     * 
     * @param n
     *            the n
     * @return the last used keys
     * @throws RemoteException
     *             the remote exception
     */
    public void getLastUsedKeys(int n) throws RemoteException
    {
        print("Get " + n + " last used keys:");
        print(collection.getInfoService().getLatestUsedKeys(n).toString());
    }

    /**
     * Gets the last registered keys.
     * 
     * @param n
     *            the n
     * @return the last registered keys
     * @throws RemoteException
     *             the remote exception
     */
    public void getLastRegisteredKeys(int n) throws RemoteException
    {
        print("Get " + n + " last registered keys:");
        print(collection.getInfoService().getLatestRegKeys(n).toString());
    }

    /**
     * Gets the most used keys.
     * 
     * @param n
     *            the n
     * @return the most used keys
     * @throws RemoteException
     *             the remote exception
     */
    public void getMostUsedKeys(int n) throws RemoteException
    {
        print("Get most used keys:");
        print(collection.getInfoService().getMostUsedKeys(n).toString());
    }

    /**
     * Prints the.
     * 
     * @param message
     *            the message
     */
    private void print(String message)
    {
        System.out.println(name + "> " + message);
    }

}
