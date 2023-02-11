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
 * @author Noah Klein
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
         * If title is present in the item populate the "News" column with it,
         * otherwise populate it with the item's description
         */
        String news = "No title available";
        if (getChildElement(item, "title") != -1) {
            XMLTree titleTree = item.child(getChildElement(item, "title"));
            if (titleTree.numberOfChildren() == 1) {
                news = titleTree.child(0).label();
            }
        } else {
            XMLTree descriptionTree = item
                    .child(getChildElement(item, "description"));
            if (descriptionTree.numberOfChildren() == 1) {
                news = descriptionTree.child(0).label();
            }
        }

        if (getChildElement(item, "link") != -1) {
            XMLTree linkTree = item.child(getChildElement(item, "link"));
            news = "<a href=\"" + linkTree.child(0).label() + "\">" + news
                    + "</a>";
        }

        String source = "No source available";
        String sourceLink = "";
        if (getChildElement(item, "source") != -1) {
            XMLTree sourceTree = item.child(getChildElement(item, "source"));
            sourceLink = sourceTree.attributeValue("url");

            if (sourceTree.numberOfChildren() == 1) {
                source = sourceTree.child(0).label();
            }
            source = "<a href=\"" + sourceLink + "\">" + source + "</a>";
        }

        String pubDate = "No date available";
        if (getChildElement(item, "pubDate") != -1) {
            pubDate = item.child(getChildElement(item, "pubDate")).child(0)
                    .label();
        }

        String htmlRow = ("  <tr>\n" + "   <td>" + pubDate + "</td>\n"
                + "   <td>" + source + "</td>\n" + "   <td>" + news + "</td>\n"
                + "  </tr>\n");

        out.print(htmlRow);
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
         * Request and store XML URL.
         */
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();

        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */
        XMLTree xml = new XMLTree1(url);

        /*
         * Ensure given XML document is an RSS 2.0 feed
         */
        if (xml.hasAttribute("version")
                && xml.attributeValue("version").equals("2.0")) {
            /*
             * Request and store filename for output, then create a SimpleWriter
             * object at that directory.
             */
            out.print("Enter the name of your html output "
                    + "(do not include the file extension): ");
            String htmlDestination = in.nextLine() + ".html";
            SimpleWriter htmlOut = new SimpleWriter1L(htmlDestination);

            /*
             * Extract <channel> element.
             */
            XMLTree channel = xml.child(0);

            /*
             * A tag and label node are both required so there is no check
             * needed to save the link
             */
            String link = channel.child(getChildElement(channel, "link"))
                    .child(0).label();

            /*
             * Check for title child and if it does not exist displayed title
             * will be "No title available"
             */
            String title = "No title available";
            if (channel.child(getChildElement(channel, "title"))
                    .numberOfChildren() == 1) {
                title = channel.child(getChildElement(channel, "title"))
                        .child(0).label();
            }

            /*
             * Check for description child and if it does not exist displayed
             * description will be "No description available"
             */
            String description = "No description available";
            if (channel.child(getChildElement(channel, "description"))
                    .numberOfChildren() == 1) {
                description = channel
                        .child(getChildElement(channel, "description")).child(0)
                        .label();
            }

            /*
             * Begin HTML file with necessary tags and first table header
             */
            String htmlHeader = "<html>\n" + "<head>\n" + "<title>" + title
                    + "</title>\n" + "</head>\n" + "<body>\n"
                    + " <h1><a href=\"" + link + "\">" + title + "</a></h1>\n"
                    + " <p>" + description + "</p>\n" + "<table border=\"1\">"
                    + "\n" + "  <tr>\n" + "   <th>Date</th>\n"
                    + "   <th>Source</th>\n" + "   <th>News</th>\n"
                    + "  </tr>\n";
            htmlOut.print(htmlHeader);

            /*
             * Search through all children of channel to find items as there is
             * no guaranteed order
             */
            for (int i = 0; i < channel.numberOfChildren(); i++) {
                /*
                 * If current node is <item> process it and output its contents
                 * to the HTML
                 */
                if (channel.child(i).label().equals("item")) {
                    processItem(channel.child(i), htmlOut);
                }
            }

            /*
             * Close initial HTML tags and then the writer
             */
            htmlOut.print("</table>\n</body>\n</html>");
            htmlOut.close();

            /*
             * Inform user that the program has completed
             */
            out.println("RSS parsed and output at " + htmlDestination);
        } else {
            out.println("Not a valid RSS 2.0 feed");
        }

        /*
         * Close I/O streams.
         */
        in.close();
        out.close();
    }

}
