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

        out.println("How many cents would you like to make change for:");
        int change = in.nextInteger();

        final int[] coinValues = { 100, 50, 25, 10, 5, 1 };

        int[] returnedCoins = new int[coinValues.length];

        int currentChange = change;

        for (int i = 0; i < coinValues.length; i++) {
            returnedCoins[i] = currentChange / coinValues[i];
            currentChange -= returnedCoins[i] * coinValues[i];
        }

        out.println();
        out.println("Dollars: " + returnedCoins[0]);
        out.println("Half Dollars: " + returnedCoins[1]);
        out.println("Quarters: " + returnedCoins[2]);
        out.println("Dimes: " + returnedCoins[3]);
        out.println("Nickles: " + returnedCoins[4]);
        out.println("Pennies: " + returnedCoins[5]);

        /*
         * Put your main program code here
         */
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
