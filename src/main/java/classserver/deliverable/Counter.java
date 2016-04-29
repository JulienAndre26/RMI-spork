package classserver.deliverable;

import java.io.Serializable;

public class Counter implements Data, Serializable {

    private static final long serialVersionUID = -1354975689448140801L;

    public int count;

    public Counter()
    {
        count = 0;
    }

    @Override
    public String getName()
    {
        return "Counter";
    }

    public void up()
    {
        System.out.println("Before " + count);
        System.out.println("After " + ++count);
    }

    public String toString()
    {
        return "Counter [" + count + "]";
    }

}
