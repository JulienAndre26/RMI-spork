package client;

import client.interfaces.DataInterface;
import client.interfaces.ServiceInterface;
import client.subscription.Publisher;
import client.subscription.Subscriber;
import client.subscription.messagelisteners.Reloader;
import client.subscription.messagelisteners.Tilter;
import collection.interfaces.CollectionServerInterface;

import javax.naming.*;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;

import static client.subscription.messagelisteners.AvailableListeners.RELOADER;
import static client.subscription.messagelisteners.AvailableListeners.TILTER;

// TODO: ClassServer needs to work in order to delete this import

/**
 * The Class Client. A client interacts with the colleciton server.
 */
public class Client {

    /** The client name. */
    private String name;

    /** The rmi url. */
    private String url;

    /** The rmi port. */
    private int port;

    /** The hashtable environment. */
    private Hashtable<String, String> hashtableEnvironment;

    /** The server context. */
    private Context context;

    /** The server collection. */
    private CollectionServerInterface collection;

    /** The last downloaded service. */
    private ServiceInterface currentService;

    private Publisher publisher;

    private void initSubscriber(String role) {
        if(role.equals(RELOADER)) // reloader listener
        this.subscriber = new Subscriber(this, new Reloader(this));
        else if (role.equals(TILTER)) // tilter listener
            this.subscriber = new Subscriber(this, new Tilter(this));
        else // default listener is a plain tilter
            this.subscriber = new Subscriber(this, new Tilter(this));
    }

    private Subscriber subscriber;

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
    public Client(String name, String url, int port, String subscriberRole)
    {
        this.name = name.toUpperCase();
        this.url = url;
        this.port = port;
        this.publisher = new Publisher(this);
        initSubscriber(subscriberRole);
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

        // Configuring connection
        hashtableEnvironment = new Hashtable<String, String>();
        hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.rmi.registry.RegistryContextFactory");
        hashtableEnvironment.put(Context.PROVIDER_URL, url + ":" + port);
        context = new InitialContext(hashtableEnvironment);

        // Getting collection server
        collection = (CollectionServerInterface) context.lookup(new CompositeName(
                "CollectionServer"));

        print("Connected");
    }

    /**
     * Put an object in the server collection.
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
    	// Checking object type
        String type = isData(o) ? "data" : (isService(o) ? "service" : "(unknown type)");

        // Putting object
        print("Put " + type + " with key \"" + key + "\"");
        collection.put(key, o);
    }

    /**
     * Checks if an object is a data.
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
     * Checks if an object is a service.
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
     * Gets an object referenced by a key from the collection server.
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

        // Checking & Casting object type
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
     * Execute the last downloaded service.
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
     * Gets the object list available on the server.
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
     * Gets the last used object keys.
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
     * Gets the last registered object keys.
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
     * Gets the most used object keys.
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
     * Prints a message with client name.
     * 
     * @param message
     *            the message
     */
    private void print(String message)
    {
        System.out.println(name + "> " + message);
    }

    public void subscribe(String topicKey){
        subscriber.subscribe(topicKey);
    }

    public void publish(String topicKey){
        publisher.publish(topicKey);
    }


    // --- getters and setters
    public String getName() {
        return name;
    }
}
