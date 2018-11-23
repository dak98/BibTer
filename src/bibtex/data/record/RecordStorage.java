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
    private String key;
    public Map fieldsMap = new LinkedHashMap<Fields,String>();

    public RecordStorage(Categories category, String key) {
        this.category = category;
        this.key = key;
    }

    @Override
    public RecordStorage get(){
        return this;
    }

    public void addField(Fields field, String value){
        fieldsMap.put(field, value);
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Categories getCategory() {
        return this.category;
    }

    public String getKey() {
        return this.key;
    }
}
