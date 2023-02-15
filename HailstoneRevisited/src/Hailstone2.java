import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class Hailstone2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone2() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        NaturalNumber hailstone = new NaturalNumber2(n);
        int seriesLen = 1;

        NaturalNumber naturalOne = new NaturalNumber2(1);
        NaturalNumber naturalTwo = new NaturalNumber2(2);
        NaturalNumber naturalThree = new NaturalNumber2(3);
        out.println("Hailstone Series begining with " + hailstone + ":");
        while (!hailstone.equals(naturalOne)) {
            out.print(hailstone + ", ");

            NaturalNumber remainder = new NaturalNumber2(hailstone)
                    .divide(naturalTwo);

            if (remainder.isZero()) {
                hailstone.divide(naturalTwo);
            } else {
                hailstone.multiply(naturalThree);
                hailstone.increment();
            }
            seriesLen++;
        }
        out.println(hailstone);
        out.println("Series Length: " + seriesLen);
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
        NaturalNumber hailStoneStart = new NaturalNumber2(in.nextInteger());
        out.println();

        generateSeries(hailStoneStart, out);
        out.println();
        out.println("Initial value is still " + hailStoneStart);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
