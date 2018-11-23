package bibtex.data;

import bibtex.data.record.RecordStorage;
import data.operations.IDataParser;
import data.operations.IDataStorage;

import java.util.List;

/**
 * Main parser for the BibTex files.
 * Parses through the text using parsers from:
 * record (@link bibtex.data.record.Parser), name (@link bibtex.data.name.Parser),
 * string.constant (@link bibtex.data.string.constant.Parser) packages.
 *
 * Implements IDataParser (@link data.operations.IDataParser).
 *
 * @author dak98
 */
public class DataParser implements IDataParser<RecordStorage> {
    /**
     * Parses specified BibTex data in order to pick out all the records.
     *
     * @param data
     *          Data to be parsed.
     * @return List of individual records from a BibTex file.
     */
    @Override
    public List<RecordStorage> parse(StringBuilder data) {
        return null;
    }

    /**
     * Stores data obtained from the parse (@link bibtex.data.DataParser.parse) method
     * in storage class (@link bibtex.data.DataStorage).
     *
     * @param dataToStore
     *          List of records to be stored.
     * @return DataStorage class instance with stored data.
     */
    @Override
    public IDataStorage store(List<RecordStorage> dataToStore) {
        IDataStorage storage = new DataStorage(dataToStore);
        return storage;
    }

}
