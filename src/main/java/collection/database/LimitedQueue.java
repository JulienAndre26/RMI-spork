package collection.database;

/**
 * Created by user on 29/04/2016.
 */
import java.util.LinkedList;

/**
 * FIFO limited generic queue.
 * 
 * @param <E>
 *            the type of the queue element
 */
public class LimitedQueue<E> extends LinkedList<E> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3966629562809071447L;

	/** The limit. */
	private int limit;

	/**
	 * Instantiates a new limited queue.
	 * 
	 * @param limit
	 *            the limit
	 */
	public LimitedQueue(int limit) {
		this.limit = limit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.LinkedList#add(java.lang.Object)
	 */
	@Override
	public boolean add(E o) {
		addFirst(o);
		while (size() > limit) {
			removeLast();
		}
		return true;
	}
}