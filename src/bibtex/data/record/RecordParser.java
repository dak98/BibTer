package bibtex.data.record;

import data.operations.IDataParser;

import java.util.List;

/**
 * Contains methods for parsing individual BibTex's record.
 * Receives data from {@link bibtex.data.DataParser}.
 * Stores data to {@link bibtex.data.record.RecordStorage}.
 *
 * Implements {@link data.operations.IDataParser}.
 *
 * @author dak98
 */
public class RecordParser implements IDataParser<String,RecordStorage> {
    /**
     * Parses an individual record to pick up category and fields.
     *
     * @param dataToParse
     *          StringBuilder with the indivual record.
     *          Should start with category name and end with ')' sign.
     * @return List containing category name and fields as Strings.
     */
    public List<String> parse(StringBuilder dataToParse) {
        
        return null;
    }

    /**
     * Stores data obtained from the {@link bibtex.data.record.RecordParser#parse(StringBuilder)}
     * in {@link bibtex.data.record.RecordParser}.
     *
     * @param dataToStore
     *          List containing category name and its fields to be stored.
     * @return {@link bibtex.data.record.RecordParser} with stored list.
     */
    public RecordStorage store(List<String> dataToStore) {
        return null;
    }

}
