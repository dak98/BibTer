package bibtex.data;

import bibtex.data.record.RecordParser;
import bibtex.data.record.RecordStorage;
import data.operations.IDataParser;

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
        DataStorage dataStorage = new DataStorage();

        IDataParser<RecordStorage> recordParser = new RecordParser();
        RecordStorage record = null;
        for (int nextStart = dataToParse.indexOf('@'); nextStart != -1; nextStart = dataToParse.indexOf('@')) {
            dataToParse = dataToParse.substring(nextStart + 1);

            record = recordParser.parse(dataToParse);

            if (record != null) {
                dataStorage.addRecord(record);
            }
        }

        return dataStorage;
    }
}
