package client;

import client.entities.DataInteger;
import client.entities.DataPi;
import client.entities.ServiceAppend;
import client.entities.ServiceSum;

import javax.naming.NamingException;
import java.io.IOException;
import java.rmi.RMISecurityManager;
import java.util.Arrays;
import java.util.List;

import static collection.subscription.messagelisteners.AvailableListeners.RELOADER;
import static collection.subscription.messagelisteners.AvailableListeners.TILTER;

/**
 * The Class MainClient which simulates both Producer and Consumer clients.
 */
public class MainClient {

	public static List<Object> sampleOne = Arrays.asList(
			(Object) new Integer(1), new Integer(3), new Integer(4));

	public static List<Object> sampleTwo = Arrays.asList(
			(Object) new Integer(2), new Integer(3),
			new String("Pas un entier"));

	public static List<Object> sampleThree = Arrays.asList((Object) new Double(
			2.5), new Float(0.3), new String("Baleine"));

	public static void sep() {
		System.out.println("--------- ---------");
	}

	public static void main(String[] args) throws NamingException,
			InterruptedException, IOException {

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		String hellDataKey = "HellData";
		String sumServiceKey = "SumService";

		Client prod = new Client("Producer", "rmi://localhost", 8082, TILTER);
		Client cons = new Client("Consumer", "rmi://localhost", 8082, TILTER);
		Client reloader = new Client("Reloader", "rmi://localhost", 8082, RELOADER);

		// Connection
		prod.connect();
		sep();
		cons.connect();
		sep();

		// Put Data in the collection
		prod.putDistantObject(hellDataKey, new DataInteger("Hell", 666));
		sep();

		// subscribe before HellData is published
		cons.subscribe(hellDataKey);

		// publish HellData
		prod.publish(hellDataKey);

		// reloader subscription
		reloader.subscribe(hellDataKey);

		// Get Data from the collection
		cons.getDistantObjectsList();
		cons.getDistantObject(hellDataKey);
		sep();

		// topiception
		prod.subscribe(hellDataKey);

		// a random guy who publish our lovely topic
		cons.publish(hellDataKey);

		// Put Service in the collection
		prod.putDistantObject(sumServiceKey, new ServiceSum(
				"L'addition pour les nuls"));
		sep();

		// publish SurService
		prod.publish(sumServiceKey);
		// subscribing
		cons.subscribe(sumServiceKey);

		// publish SurService
		prod.publish(sumServiceKey);

		// Get available objects in collection and Service
		cons.getDistantObjectsList();
		cons.getDistantObject(sumServiceKey);
		cons.executeCurrentService(sampleOne);
		cons.executeCurrentService(sampleTwo);
		sep();

		// Put Data and Service in collection
		DataPi dp = new DataPi();
		prod.putDistantObject("PiData", dp);
		ServiceAppend sa = new ServiceAppend("La concatenation pour les nuls");
		prod.putDistantObject("AppendService", sa);
		sep();

		// Get available objects, Data and Service in collection
		cons.getDistantObjectsList();
		cons.getDistantObject("PiData");
		sep();
		cons.getDistantObject("AppendService");
		cons.executeCurrentService(Arrays.asList((Object) sampleOne, sampleTwo,
				sampleThree));
		sep();

		// Get statistics
		cons.getLastRegisteredKeys(2);
		cons.getLastUsedKeys(1);
		cons.getMostUsedKeys(3);
	}
}
