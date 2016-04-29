package classserver.deliverable;

import java.io.Serializable;

/**
 * The Class Multiplicator.
 */
public class Multiplicator implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7034962950759867695L;

    /**
     * Multiply.
     *
     * @param a the a
     * @param b the b
     * @return the int
     */
    public int multiply(int a, int b)
    {
        return a * b;
    }

}
