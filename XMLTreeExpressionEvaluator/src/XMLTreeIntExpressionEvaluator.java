import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Noah Klein
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        /*
         * Retrieve the label of the current node and initialize a return value
         */
        String operation = exp.label();
        int output = 0;

        /*
         * If the current node is a number return its value as an int. If the
         * current node is an operator it must have two child nodes. Evaluate
         * these two child nodes with whatever operator is listed.
         */
        if (operation.equals("number")) {
            output = Integer.parseInt(exp.attributeValue("value"));

        } else if (operation.equals("plus")) {
            output = evaluate(exp.child(0)) + evaluate(exp.child(1));

        } else if (operation.equals("minus")) {
            output = evaluate(exp.child(0)) - evaluate(exp.child(1));

        } else if (operation.equals("times")) {
            output = evaluate(exp.child(0)) * evaluate(exp.child(1));

        } else if (operation.equals("divide")) {
            output = evaluate(exp.child(0)) / evaluate(exp.child(1));
        }

        return output;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
