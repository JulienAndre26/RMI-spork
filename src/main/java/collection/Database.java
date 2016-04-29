package collection;

import utils.LimitedQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;


/**
 * Created by user on 29/04/2016.
 */
public class Database {

    private static Database instance = null;

    // collection of registered objects
    private Map<String, Object> objectCollection;
    // historic of the 1000 last elements
    private Queue<String> keysUseHistoric;
    private Queue<String> keysRegHistoric;
    private static int MAX_ELEM = 1000;

    // constructor
    private Database() {
        flush();
    }

    // flush, resets the database
    public void flush(){
        objectCollection = new HashMap<>();
        keysUseHistoric = new LimitedQueue<>(MAX_ELEM);
        keysRegHistoric = new LimitedQueue<>(MAX_ELEM);
    }

    // getters
    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public Map<String, Object> getObjectCollection() {
        return objectCollection;
    }

    public Queue<String> getKeysUseHistoric() {
        return keysUseHistoric;
    }

    public Queue<String> getKeysRegHistoric() {
        return keysRegHistoric;
    }
}
