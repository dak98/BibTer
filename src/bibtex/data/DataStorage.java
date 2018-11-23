package bibtex.data;

import bibtex.data.record.RecordStorage;
import data.operations.IDataStorage;

import java.util.List;

/**
 * Container class for storing list of records (@link bibtex.data.record.RecordStorage).
 *
 * Implements IDataStorage (@link data.operations.IDataStorage).
 *
 * @author dak98
 */

public class DataStorage implements IDataStorage<DataStorage> {
    private List<RecordStorage> storage = null;

    public DataStorage(List<RecordStorage> recordList) {
        this.storage = recordList;
    }

    @Override
    public DataStorage get(){
        return this;
    }
}

