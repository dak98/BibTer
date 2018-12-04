package bibtex.data.record;

import bibtex.data.DataStorage;
import bibtex.data.string.constant.StringStorage;
import bibtex.syntax.Categories;
import bibtex.syntax.Fields;
import data.operations.IDataStorage;

import java.util.*;

/**
 * Container class for storing a single record.
 * See {@link bibtex.syntax.Categories} and
 * {@link bibtex.syntax.Fields} for types of data stored here.
 *
 * Implements {@link data.operations.IDataStorage}.
 *
 * @author dak98
 */
public class RecordStorage implements IDataStorage {
    private Categories category;
    private String key;
    private Map fieldsMap = new LinkedHashMap<Fields,String>();

    /**
     * Constructor for {@link bibtex.data.record.RecordStorage}.
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
     * Default constructor for {@link bibtex.data.record.RecordStorage}
     *
     */
    public RecordStorage() {}

    /**
     * Adds field to particular {@link bibtex.data.record.RecordStorage}.
     *
     * @param field
     *          Field's lastName as enum constant.
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
     *         Category of the record.
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
     * @param key
     *         Key of a record to be set.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     *
     * @return Key of the record.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Expands string constants contained in fields of
     * the record.
     * @param stringsStorage
     *         List of string constants from the BibTex file.
     */
    public void expandFields(List<StringStorage> stringsStorage) {
        Set<Map.Entry<Fields, String>> fieldsValues = getFields().entrySet();

        for (Map.Entry<Fields, String> fieldValue : fieldsValues) {
            StringBuilder expandedString = new StringBuilder();
            boolean expand = (fieldValue.getValue().indexOf("\"") == 0);
            StringTokenizer sections;
            if (expand) {
                sections = new StringTokenizer(fieldValue.getValue().substring(1), "\"");
            } else {
                sections = new StringTokenizer(fieldValue.getValue(), "\"");
            }
            while (sections.countTokens() > 0) {
                /* If '#' should be expanded */
                if (!expand) {
                    String abbrev = removeNonLetters(sections.nextToken());
                    for (StringStorage stringConstant : stringsStorage) {
                        if (stringConstant.getStringConstant(abbrev) != null) {
                            expandedString.append(stringConstant.getStringConstant(abbrev));
                            break;
                        }
                    }
                } else {
                    expandedString.append(sections.nextToken());
                }
                expand = !expand;
            }
            addField(fieldValue.getKey(), expandedString.toString());
        }
    }

    /**
     *
     * @param word
     * @return word with removed all non-letter characters.
     */
    private String removeNonLetters(String word) {
        return word.replaceAll("[^A-Za-z0-9]", "");
    }
}

