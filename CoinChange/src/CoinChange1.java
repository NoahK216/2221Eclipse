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
public final class CoinChange1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange1() {
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

        final int dollar = 100;
        final int halfDollar = 50;
        final int quarter = 25;
        final int dime = 10;
        final int nickle = 5;

        final int returnedDollars = change / dollar;

        final int returnedHalfDollars = (change % dollar) / halfDollar;

        final int returnedQuarters = (change % halfDollar) / quarter;

        final int returnedDimes = (change % quarter) / dime;

        final int returnedNickles = (change % quarter % dime) / nickle;

        final int returnedPennies = change % nickle;

        out.println();
        out.println("Dollars: " + returnedDollars);
        out.println("Half Dollars: " + returnedHalfDollars);
        out.println("Quarters: " + returnedQuarters);
        out.println("Dimes: " + returnedDimes);
        out.println("Nickles: " + returnedNickles);
        out.println("Pennies: " + returnedPennies);

        out.println(returnedDollars * dollar + returnedHalfDollars * halfDollar
                + returnedQuarters * quarter + returnedDimes * dime
                + returnedNickles * nickle + returnedPennies);

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
