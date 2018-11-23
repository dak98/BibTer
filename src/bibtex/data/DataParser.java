package bibtex.data;

import bibtex.data.record.RecordStorage;
import data.operations.IDataParser;
import data.operations.IDataStorage;

import java.util.LinkedList;
import java.util.List;

/**
 * Main parser for the BibTex files.
 * Parses through the text using {@link bibtex.data.record.RecordParser},
 * {@link bibtex.data.name.Parser} and {@link bibtex.data.string.constant.Parser}.
 *
 * Implements {@link data.operations.IDataParser}.
 *
 * @author dak98
 */
public class DataParser implements IDataParser<RecordStorage, DataStorage> {
    /**
     * Parses specified BibTex data in order to pick out all the records.
     *
     * @param dataToParse
     *          Data to be parsed.
     * @return List of individual records from a BibTex file.
     */
    @Override
    public List<RecordStorage> parse(StringBuilder dataToParse) {
        List records = new LinkedList<RecordStorage>();



        return null;
    }

    /**
     * Stores data obtained from the {@link bibtex.data.DataParser#parse(StringBuilder)}
     * in {@link bibtex.data.DataStorage}.
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
