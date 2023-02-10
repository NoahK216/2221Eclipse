import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author Noah Klein
 */
public final class Oddity3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Oddity3() {
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
        long microsPerDay = 24l * 60l * 60l * 1000l * 1000l;
        final long millisPerDay = 24 * 60 * 60 * 1000;
        out.println(microsPerDay / millisPerDay);
        out.close();
    }

}
