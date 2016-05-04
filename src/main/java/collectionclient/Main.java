package collectionclient;

import java.rmi.RemoteException;
import java.util.Arrays;

import javax.naming.NamingException;

/**
 * The Class ClientRMI.
 */
public class Main {
    
    public static void main(String[] args) throws NamingException, RemoteException, InterruptedException
    {
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());
        
        Client prod = new Client("Producer", "rmi://localhost", 8082);
        Client cons = new Client("Consumer", "rmi://localhost", 8082);
        prod.connect();
        cons.connect();
        
        prod.putDistantObject("HellData", new DataInteger("Hell", 666));
        
        cons.getContextList();
        cons.getDistantObject("HellData");
        
        prod.putDistantObject("SumService", new ServiceSum("L'addition pour les nuls"));
        
        cons.getDistantObject("SumService");
        cons.executeCurrentService(Arrays.asList((Object) new Integer(1),
                new Integer(3), new Integer(4)));
               
        cons.getLastRegisteredKeys(2);
        cons.getLastUsedKeys(1);
        cons.getMostUsedKeys(-1);
          
//        System.out.print("> Launching Producer");
//        String url = "rmi://localhost";
//        int port = 8082;
//        
//        Hashtable<String, String> hashtableEnvironment;
//        Context context;
//
//        CollectionServerInterface collection;
//
//        hashtableEnvironment = new Hashtable<String, String>();
//        hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
//                "com.sun.jndi.rmi.registry.RegistryContextFactory");
//        System.out.print(".");
//        hashtableEnvironment.put(Context.PROVIDER_URL, url + ":" + port);
//        System.out.print(".");
//        context = new InitialContext(hashtableEnvironment);
//        System.out.println(".");
//        System.out.println("> Producer launched");
//                
//        System.out.println("> Connexion... (get CollectionServer object)");
//        collection = (CollectionServerInterface) context.lookup(new CompositeName(
//                    "CollectionServer"));
//        
//        // -----------
//                
//        System.out.println(">> Put {key=KEY1; obj=TEST1}");
//        collection.put("KEY1", new ServiceSum("L'addition pour les nuls"));
//                
//        System.out.println(">> Put {key=KEY2; obj=Data1}");
//        collection.put("KEY2", new DataInteger("HellNumber", 666));
        
    }
}
