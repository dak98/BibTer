package bibtex.data.record;

import bibtex.syntax.Categories;
import bibtex.syntax.Fields;
import data.operations.IDataStorage;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Container class for storing a single record.
 * See {@link bibtex.syntax.Categories} and
 * {@link bibtex.syntax.Fields} for types of data stored here.
 *
 * Implements {@link data.operations.IDataStorage}.
 *
 * @author dak98
 */
public class RecordStorage implements IDataStorage<RecordStorage> {
    private Categories category;
    private Map fieldsMap = new LinkedHashMap<Fields,String>();

    public RecordStorage get(){
        return null;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
