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
public final class Hailstone3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone3() {
    }

    /**
     * Generates and outputs the Hailstone series and series length starting
     * with the given integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int hailstone = n;
        int seriesLen = 1;
        int seriesMax = hailstone;
        out.println("Hailstone Series begining with " + hailstone + ":");
        while (hailstone != 1) {
            out.print(hailstone + ", ");

            if (hailstone % 2 == 0) {
                hailstone = hailstone / 2;
            } else {
                hailstone = (3 * hailstone) + 1;
            }

            if (hailstone > seriesMax) {
                seriesMax = hailstone;
            }

            seriesLen++;

        }
        out.println(hailstone);
        out.println("Series Length: " + seriesLen);
        out.println("Series Max: " + seriesMax);
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        out.println("Number to start Hailstone series with: ");
        int hailStoneStart = in.nextInteger();
        out.println();

        generateSeries(hailStoneStart, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
