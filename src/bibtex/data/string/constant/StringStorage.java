package bibtex.data.string.constant;

import data.operations.IDataStorage;

import java.util.HashMap;
import java.util.Map;

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
}
