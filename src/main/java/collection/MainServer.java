package collection;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Hashtable;

/**
 * The Class MainServer which launch a single operating collection server.
 */
public class MainServer {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws NamingException
	 *             the naming exception
	 * @throws RemoteException
	 *             the remote exception
	 */
	public static void main(String[] args) throws NamingException,
			RemoteException {
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		String url = "rmi://localhost";
		int port = 8082;

		try {
			url = (args[0] == null) ? "rmi://localhost" : args[0];
			port = (args[1] == null) ? 8082 : Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		// Launching Server !
		System.out.print("SERVER> Launching on " + url + ":" + port);

		Hashtable<String, String> hashtableEnvironment = new Hashtable<String, String>();
		hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.rmi.registry.RegistryContextFactory");
		System.out.print(".");
		hashtableEnvironment.put(Context.PROVIDER_URL, url + ":" + port);
		System.out.print(".");
		CollectionServer.context = new InitialContext(hashtableEnvironment);
		System.out.println(".");
		CollectionServer.context.bind(new CompositeName("CollectionServer"),
				new CollectionServer());

		System.out.println("SERVER> Server ready\n");
		
		// Server can now treat client requests
	}
}
