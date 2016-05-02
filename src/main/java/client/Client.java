package client;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import collection.CollectionServerInterface;
import collection.InfoServiceInterface;

/**
 * The Class ClientRMI.
 */
public class Client {

    public static void printContextList(Context context) throws NamingException
    {
        NamingEnumeration<NameClassPair> list = context.list("");

        NameClassPair ncp;
        int i = -1;

        while (list.hasMoreElements())
        {
            ncp = list.nextElement();
            System.out.println(++i + ": " + ncp.getName());
        }
    }

    public static void main(String[] args) throws NamingException, RemoteException
    {
        
        System.out.println("Launching Client...");
        String url = "rmi://localhost";
        int port = 8082;
        
        Hashtable<String, String> hashtableEnvironment;
        Context context;

        CollectionServerInterface collection;

        hashtableEnvironment = new Hashtable<String, String>();
        hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.rmi.registry.RegistryContextFactory");
        hashtableEnvironment.put(Context.PROVIDER_URL, url + ":" + port);

        context = new InitialContext(hashtableEnvironment);

        System.out.println("Client launched");
        
        System.out.println("Connexion... (get CollectionServer object)");
        collection = (CollectionServerInterface) context.lookup(new CompositeName(
                    "CollectionServer"));
        
        System.out.println("Put KEY1");
        collection.put("KEY1", "TEST1");
        
        System.out.println("Get available objects in the CollectionServer");
        Client.printContextList(context);
        InfoServiceInterface infoService = (InfoServiceInterface) collection.getInfoService();
        
        System.out.println("Get 2 last puts");
        System.out.println(infoService.getLatestRegKey(2));
                

    }
}
