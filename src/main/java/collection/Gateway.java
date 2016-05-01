package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Gateway manager to access the database, thus it defines the functionalities required
 */
public class Gateway {

    private static Database db = Database.getInstance();

    /**
     * Gets n the latest registered key
     * 
     * @param n
     *            the number of info we want to get
     * @return the keys
     */
    public static List<String> getLatestRegKey(int n)
    {
        int i = 0;
        List<String> res = new ArrayList<>();
        // gets the n first registered elements
        for (String o : db.getKeysRegHistoric())
        {
            if (i == n)
                break;
            res.add(o);
            i++;
        }
        return res;
    }

    /**
     * Gets the n latest used key
     * 
     * @param n
     *            the number of key we want to get
     * @return the keys
     */
    public static List<String> getLatestUsedKey(int n)
    {
        int i = 0;
        List<String> res = new ArrayList<>();
        // gets the n first registered elements
        for (String o : db.getKeysUseHistoric())
        {
            if (i == n)
                break;
            res.add(o);
            i++;
        }
        return res;
    }

    /**
     * Gets the most used key in an given interval of time
     * 
     * @param t
     *            the interval of time (from now to now minus t (in second)) in which the keys we
     *            want are
     * @return the keys
     */
    public static List<String> getMostUsedKey(int t)
    {
        HashMap<String, Integer> count = new HashMap<>();

        // Counting key history
        for (String key : db.getKeysUseHistoric())
        {

            // TODO: add time condition here

            if (count.containsKey(key))
                count.put(key, count.get(key) + 1);
            else
                count.put(key, 1);
        }

        // Ordering keys according to counts
        List<String> res = new ArrayList<>();
        while (!count.isEmpty())
        {
            String bestKey = null;
            int bestCount = -1;

            for (Entry<String, Integer> entry : count.entrySet())
            {
                if (entry.getValue() > bestCount)
                {
                    bestKey = entry.getKey();
                    bestCount = entry.getValue();
                }
            }

            res.add(bestKey);
            count.remove(bestKey);
        }

        return res;
    }

    /**
     * Put an object with the given key to the database
     * 
     * @param key
     *            a string corresponding the object to store in the database
     * @param obj
     *            the object to store
     * @return true if success, false otherwise
     */
    public static boolean put(String key, Object obj)
    {
        // check if key already exists
        if (db.getObjectCollection().containsKey(key))
            return false;

        // add key to register historic
        db.getKeysRegHistoric().add(key);

        // add object to collection
        db.getObjectCollection().put(key, obj);

        System.out.println("PUT {key=" + key + ";obj=" + obj + "}");

        // success
        return true;
    }

    /**
     * Get object according to the given key from the database
     * 
     * @param key
     *            a string corresponding the object stored in the database
     * @return the object
     */
    public static Object get(String key)
    {
        System.out.println("GET {" + key + "}");

        // check if key already exists
        if (!db.getObjectCollection().containsKey(key))
            return null;

        // add key to use historic
        db.getKeysUseHistoric().add(key);

        // return the corresponding object
        return db.getObjectCollection().get(key);
    }

    public static void main(String[] args)
    {
        Integer o1 = 1, o2 = 2, o3 = 3, o4 = 4;
        String k1 = "a", k2 = "b", k3 = "c", k4 = "d";

        Gateway.put(k1, o1);
        Gateway.put(k2, o2);
        Gateway.put(k3, o3);
        Gateway.put(k4, o4);

        System.out.println("3 last PUT actions : " + Gateway.getLatestRegKey(3));

        System.out.println(Gateway.get(k1));
        System.out.println(Gateway.get(k3));
        System.out.println(Gateway.get(k2));
        System.out.println(Gateway.get(k4));
        System.out.println(Gateway.get(k2));
        System.out.println(Gateway.get(k2));
        System.out.println(Gateway.get(k4));

        System.out.println("4 let GET actions : " + Gateway.getLatestUsedKey(4));
        
        System.out.println("Most used keys : " + Gateway.getMostUsedKey(0));
    }
}
