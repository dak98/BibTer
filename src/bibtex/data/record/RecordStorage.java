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
    private Map fieldsMap = new LinkedHashMap<Fields,String>();

    /**
     * Default constructor for {@link bibtex.data.record.RecordStorage}.
     *
     * @param category
     *          Category of the record.
     * @param key
     *         Key of the record.
     */
    public RecordStorage(Categories category, String key) {
        this.category = category;
        this.key = key;
    }

    /**
     * Adds field to particular {@link bibtex.data.record.RecordStorage}.
     *
     * @param field
     *          Field's name as enum constant.
     * @param value
     *          Field's value.
     *
     */
    public void addField(Fields field, String value){
        fieldsMap.put(field, value);
    }

    /**
     *
     * @return All the fields of a record with their values.
     */
    public Map getFields() {
        return this.fieldsMap;
    }

    /**
     *
     * @param field
     * @return Value of given field or
     *         null if no record doesn't contain field.
     */
    public String getValueOfField(Fields field) {
        return (String)this.fieldsMap.get(field);
    }

    /**
     *
     * @param category
     *          Category of the record.
     */
    public void setCategory(Categories category) {
        this.category = category;
    }

    /**
     *
     * @return Category of the record.
     */
    public Categories getCategory() {
        return this.category;
    }

    /**
     *
     * @return Key of the record.
     */
    public String getKey() {
        return this.key;
    }
}
