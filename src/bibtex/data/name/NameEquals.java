package bibtex.data.name;

import data.operations.IDataParser;

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
        return nameParser.parse(name1).equals(nameParser.parse(name2));
    }
}
