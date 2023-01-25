import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Gives change by using as many of a denomination as possible before moving
 * onto the next lowest value.
 *
 * @author Noah Klein
 *
 */
public final class CoinChange2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange2() {
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

        out.println("How many cents would you like change for:");
        int change = in.nextInteger();

        final int[] coinValues = { 100, 50, 25, 10, 5, 1 };
        final String[] coinNames = { "Dollars", "Half Dollars", "Quarters",
                "Dimes", "Nickles", "Pennies" };
        int[] coinsReturned = new int[coinValues.length];

        for (int i = 0; i < coinValues.length; i++) {
            coinsReturned[i] = change / coinValues[i];
            change = change % coinValues[i];

            out.println(coinNames[i] + ": " + coinsReturned[i]);
        }

        in.close();
        out.close();
    }

}
