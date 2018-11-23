package bibtex.data;

import bibtex.data.record.RecordStorage;
import data.operations.IDataParser;

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
public class DataParser implements IDataParser<DataStorage> {
    /**
     * Parses specified BibTex data in order to pick out all the records.
     *
     * @param dataToParse
     *          String with data to be parsed.
     * @return List of individual records from a BibTex file.
     */
    @Override
    public DataStorage parse(String dataToParse) {
        List records = new LinkedList<RecordStorage>();



        return null;
    }
}
