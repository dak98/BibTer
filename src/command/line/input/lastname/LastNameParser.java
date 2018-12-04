package command.line.input.lastname;

import bibtex.data.name.NameParser;
import data.operations.IDataParser;

/**
 * Class responsible for parsing last names
 * given to the program through the terminal.
 *
 * @author dak98
 */
public class LastNameParser implements IDataParser {
    /**
     * @param name
     *         String to get last name from. Must contain
     *         name in one of BibTex's formats.
     * @return LastNameStorage containing return of
     *         {@link bibtex.data.name.NameParser#parse(String)}.
     */
    @Override
    public LastNameStorage parse(String name) {
        IDataParser nameParser = new NameParser();
        return new LastNameStorage((String) nameParser.parse(name));
    }
}
