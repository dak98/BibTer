package bibtex.data.name;

import data.operations.IDataParser;

import java.util.StringTokenizer;

/**
 * Class responsible for parsing names contained
 * in BibTex records.
 *
 * @author dak98
 */
public class NameParser implements IDataParser {
    /**
     * Parses names given in dataToParse to pick up last lastName.
     *
     * @param dataToParse
     *         Data containing lastName.
     * @return Last lastName (+ '|' in the case of the BibTex's 'von Last, Jr, First' format) or
     *         null if dataToParse doesn't match any of BibTex's lastName formats.
     */
    @Override
    public String parse(String dataToParse) {
        StringTokenizer nameFormat = new StringTokenizer(dataToParse, "|");
        switch (nameFormat.countTokens()) {
            case 1: {
                return getFirstFormName(dataToParse);
            }
            case 2: {
                return getSecondFormName(nameFormat.nextToken());
            }
            case 3: {
                return getSecondFormName(nameFormat.nextToken()) + "|";
            }
        }
        return null;
    }

    /**
     *
     * @param word
     * @return True if word start with upper case letter.
     *         False otherwise.
     */
    private boolean startsWithUpperCase(String word) {
        return Character.isUpperCase(removeSpaces(word).charAt(0));
    }

    /**
     *
     * @param word
     * @return word with removes white spaces (?).
     */
    private String removeSpaces(String word) {
        return word.replaceAll("\\s+","");
    }

    /**
     * String must be in the BibTex's 'First von Last' form.
     *
     * @param firstVonLastString
     * @return Last lastName from the 'First von Last' lastName format.
     */
    private String getFirstFormName(String firstVonLastString) {
        StringTokenizer wordsOfName = new StringTokenizer(firstVonLastString, " ");

        String token = null;
        /* Loop through first lastName part */
        while (wordsOfName.hasMoreTokens() && startsWithUpperCase(token = wordsOfName.nextToken()));
        /* Loop through the 'von' part */
        while (wordsOfName.hasMoreTokens() && !startsWithUpperCase(token = wordsOfName.nextToken()));

        /* If there is no 'von' part */
        if (wordsOfName.countTokens() == 0) {
            /* Return last token of StringTokenizer */
            return token;
        } else {
            /* Exited second while loop because token started with upper case */
            StringBuilder lastName = new StringBuilder(removeSpaces(token));
            while (wordsOfName.hasMoreTokens()) {
                lastName.append(" " + removeSpaces(wordsOfName.nextToken()));
            }
            return lastName.toString();
        }
    }

    /**
     * String must be in the BibTex's 'von Last, First' form.
     *
     * @param vonLastString
     * @return Last lastName from the 'von Last, First' lastName format.
     */
    private String getSecondFormName(String vonLastString) {
        StringTokenizer wordsOfName = new StringTokenizer(vonLastString, " ");

        String token = null;
        /* Loop through the 'von' part */
        while (!startsWithUpperCase(removeSpaces(token = wordsOfName.nextToken())));

        /* Exited while loop because token started with upper case */
        StringBuilder lastName = new StringBuilder(removeSpaces(token));
        while (wordsOfName.hasMoreTokens()) {
            lastName.append(" " + removeSpaces(wordsOfName.nextToken()));
        }
        return lastName.toString();

    }
}
