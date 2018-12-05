package bibtex.data.string.constant;

import data.operations.IDataStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Container class for storing a single string constant.
 *
 * Implements {@link data.operations.IDataStorage}.
 *
 * @author dak98
 */
public class StringStorage implements IDataStorage {
    private String abbrev;
    private String fullString;

    /**
     * Default constructor.
     *
     * @param abbrev
     *         Abbreviation for string constant.
     * @param fullString
     *         String constant in full.
     */
    public StringStorage(String abbrev, String fullString) {
        this.abbrev = abbrev;
        this.fullString = fullString;
    }

    /**
     *
     * @param abbrev
     * @return {@link bibtex.data.string.constant.StringStorage#fullString} if abbrev matches
     *         {@link bibtex.data.string.constant.StringStorage#abbrev}.
     *         null otherwise.
     */
    public String getStringConstant(String abbrev) {
        if (this.abbrev.equals(abbrev)) {
            return fullString;
        } else {
            return null;
        }
    }

    /**
     * Expands string constants contained in given string constant.
     * (String constants to expand must be defined before given string
     * constant in BibTex file, for the method to work correctly.
     *
     * @param listOfStringConstants
     */
    public void expandString(List<StringStorage> listOfStringConstants) {
        StringBuilder extendedString = new StringBuilder();
        StringTokenizer sections = new StringTokenizer(fullString,"#");
        if (sections.countTokens() > 1) {
            while (sections.hasMoreTokens()) {
                String section = sections.nextToken();
                if (section.contains("\"")) {
                    extendedString.append(section.substring(section.indexOf('"') + 1, section.lastIndexOf('"')));
                } else {
                    String abbrev = removeNonLetters(section);
                    for (StringStorage stringConstant : listOfStringConstants) {
                        if (stringConstant.getStringConstant(abbrev) != null) {
                            extendedString.append(stringConstant.getStringConstant(abbrev));
                            break;
                        }
                    }
                }
            }
            fullString = extendedString.toString();
        } else {
            fullString = fullString.substring(1, fullString.lastIndexOf('\"'));
        }
    }

    /**
     *
     * @param word
     * @return word with removed all non-letter characters.
     */
    private String removeNonLetters(String word) {
        return word.replaceAll("[^A-Za-z0-9]", "");
    }
}
