package bibtex.data;

import bibtex.data.record.RecordStorage;
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
    private List<RecordStorage> storage = new LinkedList<>();

    @Override
    public DataStorage get(){
        return this;
    }

    /**
     * Adds record to {@link bibtex.data.DataStorage#storage}.
     * Doesn't check whether given record is valid.
     *
     * @param record
     *          Record to be added.
     */
    public void addRecord(RecordStorage record) {
        this.storage.add(record);
    }

    public List<RecordStorage> getRecords() {
        return this.storage;
    }
}

