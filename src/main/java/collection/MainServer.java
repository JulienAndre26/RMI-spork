package collection;

import java.io.File;
import java.io.IOException;
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
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static void main(String[] args) throws NamingException, IOException
    {
        String url = "rmi://localhost";
        int port = 8082;

        System.out.println(new File(".").getCanonicalPath());

        // Launching Server !
        System.out.print("> Launching Server");

        Hashtable<String, String> hashtableEnvironment = new Hashtable<String, String>();
        hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.rmi.registry.RegistryContextFactory");
        System.out.print(".");
        hashtableEnvironment.put(Context.PROVIDER_URL, url + ":" + port);
        System.out.print(".");
        CollectionServer.context = new InitialContext(hashtableEnvironment);
        System.out.println(".");
        CollectionServer.context
                .bind(new CompositeName("CollectionServer"), new CollectionServer());

        System.out.println("> Server ready");

    }
}
