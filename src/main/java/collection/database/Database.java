package collection.database;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;


/**
 * Created by user on 29/04/2016.
 */
public class Database {

    /** The instance. */
    private static Database instance = null;

    /** The collection of registered objects. */
    private Map<String, Object> objectCollection;
    
    /** The keys use historic (1000 last elements). */
    private Queue<String> keysUseHistoric;
    
    /** The keys reg historic (1000 last elements). */
    private Queue<String> keysRegHistoric;
    
    /** The max number of elements that can be saved in queues. */
    private static int MAX_ELEM = 1000;

    /**
     * Instantiates a new database.
     */
    private Database() {
        flush();
    }

    /**
     * Flush - Reset database.
     */
    public void flush(){
        objectCollection = new HashMap<>();
        keysUseHistoric = new LimitedQueue<>(MAX_ELEM);
        keysRegHistoric = new LimitedQueue<>(MAX_ELEM);
    }

    /**
     * Gets the single instance of Database.
     *
     * @return single instance of Database
     */
    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    /**
     * Gets the object collection.
     *
     * @return the object collection
     */
    public Map<String, Object> getObjectCollection() {
        return objectCollection;
    }

    /**
     * Gets the keys use historic.
     *
     * @return the keys use historic
     */
    public Queue<String> getKeysUseHistoric() {
        return keysUseHistoric;
    }

    /**
     * Gets the keys reg historic.
     *
     * @return the keys reg historic
     */
    public Queue<String> getKeysRegHistoric() {
        return keysRegHistoric;
    }
}
