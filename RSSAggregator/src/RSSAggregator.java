import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;
import components.xmltree.XMLTree2;

/**
 * This program takes a list of a XML RSS (version 2.0) feeds from a given URL
 * and outputs an html file containing links to other html files comprised of
 * those feeds' contents.
 *
 * @author Noah Klein
 *
 */
public final class RSSAggregator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSAggregator() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        /*
         * A tag and label node are both required so there is no check needed to
         * save the link
         */
        String link = channel.child(getChildElement(channel, "link")).child(0)
                .label();

        /*
         * Check for title child and if it does not exist displayed title will
         * be "No title available"
         */
        String title = "No title available";
        if (channel.child(getChildElement(channel, "title"))
                .numberOfChildren() == 1) {
            title = channel.child(getChildElement(channel, "title")).child(0)
                    .label();
        }

        /*
         * Check for description child and if it does not exist displayed
         * description will be "No description available"
         */
        String description = "No description available";
        if (channel.child(getChildElement(channel, "description"))
                .numberOfChildren() == 1) {
            description = channel.child(getChildElement(channel, "description"))
                    .child(0).label();
        }

        /*
         * Begin HTML file with necessary tags and first table header. Indents 2
         * spaces wide.
         */
        out.println("<html>");
        out.println("<head>");
        out.println("  <title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1><a href=\"" + link + "\">" + title + "</a></h1>");
        out.println("  <p>" + description + "</p>");
        out.println("  <table border=\"1\">");
        out.println("    <tr>");
        out.println("      <th>Date</th>");
        out.println("      <th>Source</th>");
        out.println("      <th>News</th>");
        out.println("    </tr>");
    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("  </table>");
        out.println("</body>");
        out.println("</html>");
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

        /*
         * Store number of children and create a variable to hold the index of
         * the first child. This value is set to -1 so that if no child is found
         * -1 will be returned.
         */
        final int xmlChildren = xml.numberOfChildren();
        int indexOfElement = -1;

        /*
         * Cycle through all children of given XML tree until either there are
         * none left or the child with a given tag has been found.
         */
        int i = 0;
        while (i < xmlChildren && indexOfElement == -1) {
            /*
             * Set indexOfElement to the index of current child if it has the
             * required tag.
             */
            if (xml.child(i).isTag() && xml.child(i).label().equals(tag)) {
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
         * If title or description exist and have a child, fill the table with
         * that child's value. Otherwise fill the table with
         * "No title available"
         */
        String news = "No title available";
        if (getChildElement(item, "title") != -1
                && item.child(getChildElement(item, "title"))
                        .numberOfChildren() == 1) {
            news = item.child(getChildElement(item, "title")).child(0).label();
        } else if (getChildElement(item, "description") != -1
                && item.child(getChildElement(item, "description"))
                        .numberOfChildren() == 1) {
            news = item.child(getChildElement(item, "description")).child(0)
                    .label();
        }

        /*
         * Update the news column to include a hyperlink if one is present in
         * the item. If link is present in the given in the item it must have a
         * child
         */
        if (getChildElement(item, "link") != -1) {
            XMLTree linkTree = item.child(getChildElement(item, "link"));
            /*
             * Only add href tags if link is present in order to not have empty
             * hyperlinks in the table
             */
            news = "<a href=\"" + linkTree.child(0).label() + "\">" + news
                    + "</a>";
        }

        /*
         * Check first if a child with label source exists. If one does it is
         * guaranteed to have an attribute named url. Check for link to source's
         * site
         */
        String source = "No source available";
        String sourceLink = "";
        if (getChildElement(item, "source") != -1) {
            XMLTree sourceTree = item.child(getChildElement(item, "source"));
            /*
             * If source exists a "url" attribute is guaranteed
             */
            sourceLink = sourceTree.attributeValue("url");

            /*
             * Check if the listed source has a name to be displayed. If not,
             * display a message indicate that there is a source but no name.
             */
            if (sourceTree.numberOfChildren() == 1) {
                source = sourceTree.child(0).label();
            } else {
                source = "No title available";
            }

            /*
             * Format the source so that the earlier hyperlink will be used
             * instead of plain text. Only add href tags if source is present in
             * order to not have empty hyperlinks in the table
             */
            source = "<a href=\"" + sourceLink + "\">" + source + "</a>";
        }

        /*
         * Initialize the final column and to indicate no date available and
         * then update it to the provided date if there is one.
         */
        String pubDate = "No date available";
        if (getChildElement(item, "pubDate") != -1) {
            pubDate = item.child(getChildElement(item, "pubDate")).child(0)
                    .label();
        }

        /*
         * Format all of the above into a single 3 wide row comprised of valid
         * HTML. Then write those to the given stream.
         */
        out.println("    <tr>");
        out.println("       <td>" + pubDate + "</td>");
        out.println("       <td>" + source + "</td>");
        out.println("       <td>" + news + "</td>");
        out.println("    </tr>");
    }

    /**
     * Processes one XML RSS (version 2.0) feeds from a given URL converting it
     * into the corresponding HTML output file.
     *
     * @param url
     *            the URL of the RSS feeds
     * @param file
     *            the name of the HTML output file
     * @param out
     *            the output stream to report progress or errors
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * [reads RSS feeds from url, saves HTML document with table of news items
     *   to file, appends to out.content any needed messages]
     * </pre>
     */
    private static void processfeed(String url, String file, SimpleWriter out) {
        XMLTree xml = new XMLTree1(url);
        /*
         * Ensure given XML document is an RSS 2.0 feeds
         */
        if (xml.label().equals("rss") && xml.hasAttribute("version")
                && xml.attributeValue("version").equals("2.0")) {

            /*
             * Extract <channel> element and use it to output the file's header.
             */
            XMLTree channel = xml.child(0);
            outputHeader(channel, out);

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
                    processItem(channel.child(i), out);
                }
            }

            /*
             * Close initial HTML tags but not the writer as it will be used in
             * other feeds
             */
            outputFooter(out);
        }
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
         * Request and store XML URL and final file name, and create their
         * respective objects
         */
        out.print("Enter the URL to a list of RSS 2.0 news feeds in XML: ");
        String url = in.nextLine();
        XMLTree feeds = new XMLTree2(url);

        out.print("Enter file name of the index page to create: ");
        String mainHTML = in.nextLine();
        SimpleWriter mainHTMLOut = new SimpleWriter1L(mainHTML);

        /*
         * Write the required headers to the index page
         */
        String feedsTitle = feeds.attributeValue("title");
        mainHTMLOut.println("<html>");
        mainHTMLOut.println("  <head>");
        mainHTMLOut.println("    <title>" + feedsTitle + "</title>");
        mainHTMLOut.println("  </head>");
        mainHTMLOut.println("  <body>");
        mainHTMLOut.println("    <h2>" + feedsTitle + "</h2>");
        mainHTMLOut.println("    <ul>");

        /*
         * Loop through the children in the XMLTree provided from the list of
         * RSS feeds. Process each one of the RSS feeds and output them to their
         * respective files.
         */
        for (int i = 0; i < feeds.numberOfChildren(); i++) {
            /*
             * Read attributes from the current feed element
             */
            String rssURL = feeds.child(i).attributeValue("url");
            String rssName = feeds.child(i).attributeValue("name");
            String rssFile = feeds.child(i).attributeValue("file");

            /*
             * Create an out stream to the given file, then process that RSS
             * feed to that file and close the out stream after.
             */
            SimpleWriter rssOut = new SimpleWriter1L(rssFile);
            processfeed(rssURL, rssName, rssOut);
            rssOut.close();

            /*
             * Add references to processed feed to index page, two print
             * statements are used in order to have proper spacing because
             * eclipse does not recognize space characters on the next line.
             */
            mainHTMLOut.print("      ");
            mainHTMLOut.println(
                    "<li><a href=" + rssFile + ">" + rssName + "</a></li>");
        }

        /*
         * Close the index page and then the writer for it
         */
        mainHTMLOut.println("    </ul>");
        mainHTMLOut.println("  </body>");
        mainHTMLOut.println("</html>");

        mainHTMLOut.close();

        /*
         * Inform user that the task has completed
         */
        out.println("RSS Feeds processed and output at " + mainHTML);

        /*
         * Close I/O streams.
         */
        in.close();
        out.close();
    }

}
