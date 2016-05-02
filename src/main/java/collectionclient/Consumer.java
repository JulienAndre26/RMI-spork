package collectionclient;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import utils.Utils;
import collection.CollectionServerInterface;
import collection.DataInterface;
import collection.InfoServiceInterface;

/**
 * The Class ClientRMI.
 */
public class Consumer {
    
    public static void main(String[] args) throws NamingException, RemoteException, InterruptedException
    {
        
        System.out.print("> Launching Consumer");
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
        System.out.println("> Consumer launched");
        
        System.out.println("> Connexion... (get CollectionServer object)");
        collection = (CollectionServerInterface) context.lookup(new CompositeName(
                    "CollectionServer"));
        
        // --------
                        
        System.out.println(">> Get Context List :");
        Utils.printContextList(context);
        InfoServiceInterface infoService = (InfoServiceInterface) collection.getInfoService();
        
        System.out.println(">> Get 2 last puts");
        System.out.println(infoService.getLatestRegKey(2));
        
        System.out.println(">> Get KEY1");
        DataInterface data = collection.get("KEY1");
        String value = (String) data.getObject();
        System.out.println(">> Object value : " + value);
                
        System.out.println(">> Get Context List :");
        Utils.printContextList(context);
                
        System.out.println(">> Get KEY2");
        data = collection.get("KEY2");
        value = ((TestObject) data.getObject()).getMsg();
        System.out.println("Object value : " + value);
        
        System.out.println(">> Get KEY2");
        collection.get("KEY2");
        
        System.out.println(">> Get Most Used Keys :");
        System.out.println(infoService.getMostUsedKey(0));
                
    }
}
