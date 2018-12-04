package bibtex.data;

import bibtex.data.record.RecordParser;
import bibtex.data.record.RecordStorage;
import bibtex.data.string.constant.StringParser;
import bibtex.data.string.constant.StringStorage;
import data.operations.IDataParser;
import data.operations.IDataStorage;
import utility.LineCounter;

/**
 * Main parser for the BibTex files.
 * Parses through the text using {@link bibtex.data.record.RecordParser}
 * and {@link bibtex.data.string.constant.StringParser}.
 *
 * Implements {@link data.operations.IDataParser}.
 *
 * @author dak98
 */
public class DataParser implements IDataParser {
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
        LineCounter lineCounter = new LineCounter();

        for (int nextStart = dataToParse.indexOf('@'); nextStart != -1; nextStart = dataToParse.indexOf('@')) {
            /* If '@' means new BibTex object */
            if ((dataToParse = dataToParse.substring(nextStart + 1)).charAt(0) != ' ') {
                lineCounter.increment();
                IDataParser parser = new StringParser();
                StringStorage stringConstant = (StringStorage) parser.parse(dataToParse);
                if (stringConstant != null) {
                    dataStorage.addStringConstant(stringConstant);
                }
                parser = new RecordParser();
                RecordStorage record = (RecordStorage) parser.parse(dataToParse.substring(0, dataToParse.indexOf('}') + 1));
                if (record != null) {
                    dataStorage.addRecord(record);
                    dataToParse = dataToParse.substring(dataToParse.indexOf('}'));
                } else {
                    lineCounter.decrement();
                }
            } else {
                lineCounter.increment();
            }
        }
        return dataStorage;
    }
}