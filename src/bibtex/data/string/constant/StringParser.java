package bibtex.data.string.constant;

import bibtex.syntax.KeyWords;
import bibtex.syntax.ToEnumConverter;
import data.operations.IDataParser;
import utility.LineCounter;

/**
 * Contains methods for parsing string constant of a BibTex file.
 * Receives data from {@link bibtex.data.DataParser}.
 * Stores data to {@link bibtex.data.string.constant.StringStorage}.
 *
 * Implements {@link data.operations.IDataParser}.
 *
 * @author dak98
 */
public class StringParser implements IDataParser {
    /**
     * Parses a string constant.
     *
     * @param dataToParse
     *          String with a string constant.
     *          Should start with string constant and end with '}' sign.
     * @return Instance of StringStorage representing an string constant or
     *         null if it is not a string constant.
     * @throws NullPointerException
     *          dataToParse is null.
     * @throws IndexOutOfBoundsException
     *          No key or key value of string constant.
     */
    @Override
    public StringStorage parse(String dataToParse) {
        LineCounter lineCounter = new LineCounter();

        if (dataToParse == null) {
            throw new NullPointerException();
        }

        if (isStringConstant(dataToParse)) {
            dataToParse = dataToParse.substring(dataToParse.indexOf('(') + 1);

            String abbrev = getKey(dataToParse);
            dataToParse = dataToParse.substring(dataToParse.indexOf('=') + 3);

            String fullString = getValue(dataToParse);

            return new StringStorage(abbrev, fullString);
        } else {
            return null;
        }
    }

    /**
     * @param dataToParse
     * @return true if dataToParse starts with "string(",
     *         false otherwise.
     */
    private boolean isStringConstant(String dataToParse) {
        ToEnumConverter converter = new ToEnumConverter();
        boolean answer = true;
        try {
            answer = converter.toKeyWord(dataToParse.substring(0, dataToParse.indexOf('(')).toLowerCase()) == KeyWords.string;
        } catch (Exception e) {
            return false;
        }
        return answer;
    }

    /**
     * Parses data to get key of a string constant.
     *
     * @param dataToParse
     *          String to get record's key from.
     *          It must start with char sequence to be checked and
     *          end with a space.
     * @return Key of a string constant.
     * @throws IndexOutOfBoundsException
     *          No key of string constant found.
     */
    private String getKey(String dataToParse) {
        String key;
        try {
            key = dataToParse.substring(0, dataToParse.indexOf(' '));
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }

        return key;
    }


    /**
     *
     * @param dataToParse
     * @return Value of a string constant.
     * @throws IndexOutOfBoundsException
     *          No value of string constant found.
     */
    private String getValue(String dataToParse) {
        String value;
        try {
            value = dataToParse.substring(0, dataToParse.indexOf('"'));
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }

        return value;
    }

}
