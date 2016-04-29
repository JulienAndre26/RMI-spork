package classserver.deliverable;

import java.io.Serializable;

/**
 * The Class Counter.
 */
public class Counter implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8775544040424915880L;

    /** The count. */
    private int count;

    /**
     * Instantiates a new counter.
     */
    public Counter()
    {
        count = 0;
    }

    /**
     * Up.
     * 
     * @return the int
     */
    public synchronized int up()
    {
        System.out.println("Before : " + count);
        count++;
        System.out.println("After : " + count);
        return count;
    }

}
