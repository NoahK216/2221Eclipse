import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Noah Klein
 *
 */
public final class Part3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Part3() {
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

        final int iterations = 10;

        int s = 0;
        int i = 0;
        while (i <= 10) {
            s = s + i;
            i++;
        }

        out.println(s);

        in.close();
        out.close();
    }

}
