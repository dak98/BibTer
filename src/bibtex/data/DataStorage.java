package bibtex.data;

import bibtex.data.record.RecordStorage;
import bibtex.data.string.constant.StringStorage;
import data.operations.IDataStorage;

import java.util.LinkedList;
import java.util.List;

/**
 * Container class for storing list of {@link bibtex.data.record.RecordStorage}.
 *
 * Implements {@link data.operations.IDataStorage}.
 *
 * @author dak98
 */

public class DataStorage implements IDataStorage<DataStorage> {
    private List<RecordStorage> records = new LinkedList<>();
    private List<StringStorage> stringConstants = new LinkedList<>();

    @Override
    public DataStorage get(){
        return this;
    }

    /**
     * Adds record to {@link bibtex.data.DataStorage#records}.
     * Doesn't check whether given record is valid.
     *
     * @param record
     *          Record to be added.
     */
    public void addRecord(RecordStorage record) {
        this.records.add(record);
    }

    /**
     * Adds string constant to {@link bibtex.data.DataStorage#stringConstants}.
     * Doesn't check whether given string constant is valid.
     *
     * @param stringConstant
     *          String constant to be added.
     */
    public void addStringConstant(StringStorage stringConstant) {
        this.stringConstants.add(stringConstant);
    }

    public List<RecordStorage> getRecords() {
        return this.records;
    }
}

