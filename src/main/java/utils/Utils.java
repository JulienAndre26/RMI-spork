package utils;

import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class Utils {

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
}
