import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points inside a
 * unit circle on a 2 by 2 plane.
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point`
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        return (Math.sqrt(
                (xCoord - 1) * (xCoord - 1) + (yCoord - 1) * (yCoord - 1)) < 1);
    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {
        Random rnd = new Random1L();

        int ptsInInterval = 0, ptsInSubinterval = 0;
        /*
         * Generate points and count how many fall in [0.0,0.5) interval
         */
        while (ptsInInterval < n) {
            /*
             * Generate coordinate pairs
             */
            double x = rnd.nextDouble();
            double y = rnd.nextDouble();

            ptsInInterval++;

            if (pointIsInCircle(x, y)) {
                ptsInSubinterval++;
            }
        }
        return ptsInSubinterval;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        /*
         * Declare counters and initialize them
         */
        int inCircle = numberOfPointsInCircle(n);

        final double squareArea = 4.0;
        double estimatedArea = (squareArea * inCircle) / n;
        output.println("Estimated area of unit circle: " + estimatedArea);
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }

}
