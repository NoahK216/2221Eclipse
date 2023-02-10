import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author P. Bucci
 */
public final class HelloWorld {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private HelloWorld() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */

    /**
     * Main method.
     *
     * @param ch
     *            the command line arguments; unused here
     *
     * @return nice
     */
    private static boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        String userIn = in.nextLine();
        int vowelsInString = 0;
        for (int i = 0; i < userIn.length(); i++) {
            if (isVowel(userIn.charAt(i))) {
                vowelsInString++;
            }
        }
        out.println(vowelsInString);

        out.close();
        in.close();
    }

}
