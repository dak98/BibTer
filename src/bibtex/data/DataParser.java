package bibtex.data;

import bibtex.data.record.RecordParser;
import bibtex.data.record.RecordStorage;
import bibtex.data.string.constant.StringParser;
import bibtex.data.string.constant.StringStorage;
import data.operations.IDataParser;

/**
 * Main parser for the BibTex files.
 * Parses through the text using {@link bibtex.data.record.RecordParser}
 * and {@link bibtex.data.string.constant.StringParser}.
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

        for (int nextStart = dataToParse.indexOf('@'); nextStart != -1; nextStart = dataToParse.indexOf('@')) {
            dataToParse = dataToParse.substring(nextStart + 1);

            IDataParser parser = new RecordParser();
            RecordStorage record = (RecordStorage)parser.parse(dataToParse);
            parser = new StringParser();
            StringStorage stringConstant = (StringStorage)parser.parse(dataToParse);

            if (record != null) {
                dataStorage.addRecord(record);
            } else if (stringConstant != null) {
                dataStorage.addStringConstant(stringConstant);
            }
        }

        return dataStorage;
    }
}
