import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program uses a user specified amount of points placed randomly on a
 * plane to determine the area of a unit circle.
 *
 * @author Noah Klein
 *
 */
public final class MonteCarlo {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private MonteCarlo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * IO Library instantiations
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Get user input that determines how many points will be placed
         */
        out.print("Number of points: ");
        int n = in.nextInteger();

        /*
         * ptsInInterval keeps track of how many points have been checked, while
         * ptsInSubInterval keeps track of how many of these points land within
         * our circle
         */
        int ptsInInterval = 0, ptsInSubinterval = 0;

        Random rnd = new Random1L();

        /*
         * Place and calculate a points positions however many times the user
         * specified.
         */
        while (ptsInInterval < n) {
            /*
             * Generate a coordinate for x and y between [0,2)
             */
            double x = rnd.nextDouble() * 2;
            double y = rnd.nextDouble() * 2;

            ptsInInterval++;
            /*
             * Use the distance formula to determine if the generated points are
             * outside of a circle with radius 1, located at (1,1)
             */
            if (Math.sqrt((x - 1) * (x - 1) + (y - 1) * (y - 1)) <= 1) {
                ptsInSubinterval++;
            }
        }

        /*
         * Calculate what percentage of tested points landed inside of the
         * circle
         */
        double percentHit = (double) ptsInSubinterval / ptsInInterval;

        /*
         * Since the possible coordinates our points could land on were [0,2)
         * for both x and y, The total area they could land in is 4 units large
         *
         * Multiplying the percent of points that landed inside of the circle by
         * the area of the overall grid should give us a good estimate of the
         * area of the circle
         */
        final double gridArea = 4.0;
        double estimate = percentHit * gridArea;
        out.println("Estimated area of circle with radius 1: " + estimate);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
