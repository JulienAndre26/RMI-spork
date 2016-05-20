package client;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;

import client.entities.DataInteger;
import client.entities.DataPi;
import client.entities.ServiceAppend;
import client.entities.ServiceSum;

/**
 * The Class MainClient which simulates both Producer and Consumer clients.
 */
public class MainClient {

    public static List<Object> sampleOne = Arrays.asList((Object) new Integer(1), new Integer(3),
            new Integer(4));

    public static List<Object> sampleTwo = Arrays.asList((Object) new Integer(2), new Integer(3),
            new String("Pas un entier"));

    public static List<Object> sampleThree = Arrays.asList((Object) new Double(2.5),
            new Float(0.3), new String("Baleine"));

    public static void sep()
    {
        System.out.println("--------- ---------");
    }

    public static void main(String[] args) throws NamingException, InterruptedException, IOException
    {
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        Client prod = new Client("Producer", "rmi://localhost", 8082);
        Client cons = new Client("Consumer", "rmi://localhost", 8082);

        // Connection
        prod.connect();
        sep();
        cons.connect();
        sep();
        
        // Put Data in the collection
        prod.putDistantObject("HellData", new DataInteger("Hell", 666));
        sep();
        
        // Get Data from the collection
        cons.getDistantObjectsList();
        cons.getDistantObject("HellData");
        sep();
        
        // Put Service in the collection
        prod.putDistantObject("SumService", new ServiceSum("L'addition pour les nuls"));
        sep();
        
        // Get available objects in collection and Service
        cons.getDistantObjectsList();
        cons.getDistantObject("SumService");
        cons.executeCurrentService(sampleOne);
        cons.executeCurrentService(sampleTwo);
        sep();
        
        // Put Data and Service in collection
        prod.putDistantObject("PiData", new DataPi());
        prod.putDistantObject("AppendService", new ServiceAppend("La concat�nation pour les nuls"));
        sep();
        
        // Get available objects, Data and Service in collection
        cons.getDistantObjectsList();
        cons.getDistantObject("PiData");
        sep();
        cons.getDistantObject("AppendService");
        cons.executeCurrentService(Arrays.asList((Object) sampleOne, sampleTwo, sampleThree));
        sep();
        
        // Get statistics
        cons.getLastRegisteredKeys(2);
        cons.getLastUsedKeys(1);
        cons.getMostUsedKeys(3);
    }
}
