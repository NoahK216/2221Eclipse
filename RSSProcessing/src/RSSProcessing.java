import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * This program inputs an XML RSS (version 2.0) feed from a given URL and
 * outputs various elements of the feed to the console.
 *
 * @author Put your name here
 *
 */
public final class RSSProcessing {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSProcessing() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of the {@code XMLTree} matching the
     *         given tag or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of the {@code XMLTree} matching the
     *   given tag or -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        final int xmlChildren = xml.numberOfChildren();
        int indexOfElement = -1;

        int i = 0;
        while (i < xmlChildren && indexOfElement == -1) {
            if (xml.child(i).label().equals(tag)) {
                indexOfElement = i;
            }
            i++;
        }

        return indexOfElement;
    }

    /**
     * Processes one news item and outputs the title, or the description if the
     * title is not present, and the link (if available) with appropriate
     * labels.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures out.content = #out.content * [the title (or description) and
     *          link]
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";
        /*
         * TODO: #3 - fill in body
         */

    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and returns
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the label of the first child of the {@code XMLTree} matching the
     *         given tag or a String indicating otherwise if not found
     * @requires [the label of the root of xml is a tag and the child with the
     *           given tag has 1 or 0 children]
     * @ensures <pre>
     * getChildElement =
     *  [the label of the first child of the {@code XMLTree} matching the
     *   given tag] or [tag + "was not found"] if not found
     * </pre>
     */
    private static String getChildLabel(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        final int xmlChildren = xml.numberOfChildren();
        boolean found = false;
        String childLabel = tag + " not found";

        int i = 0;
        while (i < xmlChildren && !found) {
            if (xml.child(i).label().equals(tag)
                    && xml.child(i).numberOfChildren() == 1) {
                childLabel = xml.child(i).child(0).label();
                found = true;
            }
            i++;
        }

        return childLabel;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open I/O streams.
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Input the source URL.
         */
        //out.print("Enter the URL of an RSS 2.0 news feed: ");
        //String url = in.nextLine();
        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */
        XMLTree xml = new XMLTree1(
                "https://www.feedforall.com/sample-feed.xml");
        /*
         * Extract <channel> element.
         */
        XMLTree channel = xml.child(0);

        out.println("Title: " + getChildLabel(channel, "title"));
        out.println("Link: " + getChildLabel(channel, "link"));
        out.println("Description: " + getChildLabel(channel, "description"));

        /*
         * TODO: #4 - for each item, output title (or description, if title is
         * not available) and link (if available)
         */

        /*
         * Close I/O streams.
         */
        in.close();
        out.close();
    }

}