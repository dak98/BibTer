package bibtex.data.name;

import data.operations.IDataParser;

import java.util.StringTokenizer;

/**
 * Class responsible if to given names are equal
 * (have the same last lastName).
 *
 * @author dak98
 */
public class NameEquals {
    /**
     *
     * @param name1
     * @param name2
     * @return True if name1 has the same last lastName as name2.
     *         False otherwise.
     */
    public boolean equals(String name1, String name2) {
        IDataParser nameParser = new NameParser();
        StringTokenizer name1Tokens = new StringTokenizer(name1, " and ");


        boolean equal = false;
        while (name1Tokens.countTokens() > 0 && !equal) {
            String name1Token = name1Tokens.nextToken();
            StringTokenizer name2Tokens = new StringTokenizer(name2, " and ");
            while (name2Tokens.countTokens() > 0 && !equal) {
                equal = nameParser.parse(name1Token).equals(nameParser.parse(name2Tokens.nextToken()));
            }
        }
        return equal;
    }
}
