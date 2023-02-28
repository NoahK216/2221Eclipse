import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramWithIOAndStaticMethod {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ProgramWithIOAndStaticMethod() {
    }

    /**
     * @return n^p
     *
     * @param n
     *            number to be raised
     *
     * @param p
     *            power to raise n to
     */
    private static int power(int n, int p) {
        int raised = 1;
        if (p > 0) {
            raised = n * power(n, p - 1);
        }
        return raised;
    }

    /**
     *
     * @return n^p
     *
     * @param n
     *            number to be raised
     *
     * @param p
     *            power to raise n to
     */
    private static int powerEven(int n, int p) {
        int raised = 1;
        if (p > 0) {
            raised = power(n, p / 2);
            raised = raised * raised;
            if (p % 2 != 0) {
                raised = raised * n;
            }
        }
        return raised;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println(power(0, 2));
        out.println(powerEven(2, 3));

        in.close();
        out.close();
    }

}
