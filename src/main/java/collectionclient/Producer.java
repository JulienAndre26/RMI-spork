package collectionclient;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import collection.CollectionServerInterface;

/**
 * The Class ClientRMI.
 */
public class Producer {
    
    public static void main(String[] args) throws NamingException, RemoteException, InterruptedException
    {
        
        System.out.print("> Launching Producer");
        String url = "rmi://localhost";
        int port = 8082;
        
        Hashtable<String, String> hashtableEnvironment;
        Context context;

        CollectionServerInterface collection;

        hashtableEnvironment = new Hashtable<String, String>();
        hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.rmi.registry.RegistryContextFactory");
        System.out.print(".");
        hashtableEnvironment.put(Context.PROVIDER_URL, url + ":" + port);
        System.out.print(".");
        context = new InitialContext(hashtableEnvironment);
        System.out.println(".");
        System.out.println("> Producer launched");
                
        System.out.println("> Connexion... (get CollectionServer object)");
        collection = (CollectionServerInterface) context.lookup(new CompositeName(
                    "CollectionServer"));
        
        // -----------
                
        System.out.println(">> Put {key=KEY1; obj=TEST1}");
        collection.put("KEY1", "TEST1");
                
        System.out.println(">> Put {key=KEY2; obj=TestObject1}");
        collection.put("KEY2", new TestObject("Le test a reussi"));
        
    }
}
