package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.naming.NamingException;

/**
 * The Class ClientRMI.
 */
public class Client {

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     * @throws NotBoundException
     *             the not bound exception
     * @throws NamingException
     * @throws IOException 
     */
    public static void main(String[] args) throws NotBoundException,
            NamingException, IOException
    {
        /*
         * Hashtable<String, String> hashtableEnvironment = new Hashtable<String, String>();
         * hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
         * "com.sun.jndi.rmi.registry.RegistryContextFactory");
         * hashtableEnvironment.put(Context.PROVIDER_URL, "rmi://localhost:8082"); Context context =
         * new InitialContext(hashtableEnvironment); NamingEnumeration<NameClassPair> list =
         * context.list(""); NameClassPair ncp; int i = -1; while (list.hasMoreElements()) { ncp =
         * list.nextElement(); System.out.println(++i + ": " + ncp); }
         */

        String serverHostname = new String("localhost");
        int port = 8082;

        if (args.length > 0)
            serverHostname = args[0];
        System.out.println("Attemping to connect to host " + serverHostname + " on port " + port + ".");

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try
        {
            echoSocket = new Socket(serverHostname, port);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        }
        catch (UnknownHostException e)
        {
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println("Couldn't get I/O for " + "the connection to: " + serverHostname);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        System.out.print("input: ");
        while ((userInput = stdIn.readLine()) != null)
        {
            out.println(userInput);
            System.out.println("echo: " + in.readLine());
            System.out.print("input: ");
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }

    // Remote obj = Naming.lookup("http://BXXX:8082/");
    // System.out.println(obj);

}
