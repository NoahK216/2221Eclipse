import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author Noah Klein
 */
public final class Oddity2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Oddity2() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        double x = 456.0;
        double y = 100.0 * 4.56;
        if (Math.abs(x - y) < 0.00001) {
            out.println("equal");
        } else {
            out.println("not equal");
        }
        out.close();
    }

}
