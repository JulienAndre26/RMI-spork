package collection;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MainServer {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws NamingException
	 *             the naming exception
	 * @throws RemoteException
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws NamingException,
			RemoteException {
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
	}
}
