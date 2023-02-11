import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
     * Creates file at specified directory with given String of contents.
     *
     * @param fileName
     *            path for file to be created including extension
     * @param contents
     *            String to be written into fileName
     * @param append
     *            false if you would like to overwrite any existing text in the
     *            file, true to append
     * @requires [fileName must not have any characters invalid for fileName or
     *           spaces]
     * @ensures file with name fileName will be created at the base directory of
     *          this project filled with contents
     */
    private static void writeToFile(String fileName, String contents,
            boolean append) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileName, append))) {
            writer.append(contents);
            writer.close();

        } catch (IOException error) {
            error.printStackTrace();
        }
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
        String news = "No title or description avaliable";
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

        String newsLink = "";
        if (getChildElement(item, "link") != -1) {
            newsLink = item.child(getChildElement(item, "link")).child(0)
                    .label();
        }

        String source = "No source available";
        String sourceLink = "";
        if (getChildElement(item, "source") != -1) {
            XMLTree sourceTree = item.child(getChildElement(item, "source"));
            sourceLink = sourceTree.attributeValue("url");
            if (sourceTree.numberOfChildren() == 1) {
                source = sourceTree.child(0).label();
            }

        }

        String pubDate = "";
        if (getChildElement(item, "pubDate") != -1) {
            pubDate = item.child(getChildElement(item, "pubDate")).child(0)
                    .label();
        }

        String htmlRow = ("  <tr>\n" + "   <td>" + pubDate + "</td>\n"
                + "   <td><a href=\"" + sourceLink + "\">" + source
                + "</a></td>\n" + "   <td><a href=\"" + newsLink + "\">" + news
                + "</a></td>\n" + "  </tr>\n");

        writeToFile("output.html", htmlRow, true);
    }

    /**
     * ADD A DESCRIPTION.
     *
     * @param line
     *            line to be concatenated
     * @ensures newLine = [line + "\n"]
     *
     * @return [line + "\n"]
     */
    private static String htmlHeader(String title, String titleLink,
            String description) {
        return ("<html>\n" + "<head>\n" + "<title>" + title + "</title>\n"
                + "</head>\n" + "<body>\n" + " <h1><a href=\"" + titleLink
                + "\">" + title + "</a></h1>\n" + " <p>" + description
                + "</p>\n" + "<table border=\"1\">" + "\n" + "  <tr>\n"
                + "   <th>Date</th>\n" + "   <th>Source</th>\n"
                + "   <th>News</th>\n" + "  </tr>\n");
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
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();
        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */

        XMLTree xml = new XMLTree1(url);
        /*
         * Extract <channel> element.
         */
        XMLTree channel = xml.child(0);

        String link = channel.child(getChildElement(channel, "link")).child(0)
                .label();

        String title = "No title avaliable";
        if (channel.child(getChildElement(channel, "title"))
                .numberOfChildren() == 1) {
            title = channel.child(getChildElement(channel, "title")).child(0)
                    .label();
        }

        String description = "No description avaliable";
        if (channel.child(getChildElement(channel, "description"))
                .numberOfChildren() == 1) {
            description = channel.child(getChildElement(channel, "description"))
                    .child(0).label();
        }

        /*
         * Begin html file with necessary tags and first table header, overwrite
         * any existing files.
         */
        writeToFile("output.html", htmlHeader(title, link, description), false);

        /*
         * Ensure that the RSS feed has an item before processing them
         */
        if (getChildElement(channel, "item") != -1) {
            /*
             * Search through all children of channel as there is no guaranteed
             * order
             */
            for (int i = 0; i < channel.numberOfChildren(); i++) {
                if (channel.child(i).label().equals("item")) {
                    processItem(channel.child(i), out);
                }
            }

        }

        /*
         * Close initial HTML tags
         */
        writeToFile("output.html", "</table>\n" + "</body>\n" + "</html>",
                true);

        /*
         * Close I/O streams.
         */
        in.close();
        out.close();
    }

}
