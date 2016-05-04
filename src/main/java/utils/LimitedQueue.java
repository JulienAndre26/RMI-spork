package utils;

/**
 * Created by user on 29/04/2016.
 */
import java.util.LinkedList;

/**
 * FIFO limited generic queue
 * @param <E> the type of the queue element
 */
public class LimitedQueue<E> extends LinkedList<E> {

    private static final long serialVersionUID = 3966629562809071447L;

    private int limit;

    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E o) {
        addFirst(o);
        while (size() > limit) { removeLast(); }
        return true;
    }
}