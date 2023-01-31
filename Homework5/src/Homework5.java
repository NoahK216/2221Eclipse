import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Test program for Homework 5
 *
 * @author Noah Klein
 *
 */
public final class Homework5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Homework5() {
    }

    private static boolean allTheSame(int x, int y, int z) {
        return x == y && x == z;
    }

    private static boolean allDifferent(int x, int y, int z) {
        return x != y && x != z && y != z;
    }

    private static boolean sorted(int x, int y, int z) {
        return x <= y && y <= z;
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

        out.println(allTheSame(1, 2, 3));
        out.println(allTheSame(1, 1, 1));
        out.println();

        out.println(allDifferent(1, 2, 3));
        out.println(allDifferent(1, 1, 1));
        out.println();

        out.println(sorted(3, 2, 1));
        out.println(sorted(1, 2, 3));

        in.close();
        out.close();
    }

}
