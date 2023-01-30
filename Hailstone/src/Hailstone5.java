import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone5() {
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        String userIn = in.nextLine();
        while (!FormatChecker.canParseInt(userIn)
                || Integer.parseInt(userIn) <= 0) {
            out.println("Please input a positive integer");
            userIn = in.nextLine();
        }
        return Integer.parseInt(userIn);
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

        boolean continueCalc = true;

        while (continueCalc) {
            out.println("Number to start Hailstone series with: ");
            int hailStoneStart = getPositiveInteger(in, out);
            out.println();

            generateSeries(hailStoneStart, out);

            out.println();
            out.println("Would you like to generate another series (y/n)");
            String response = in.nextLine();
            continueCalc = response.contentEquals("y");
            out.println();
        }

        in.close();
        out.close();
    }

}
