package bibtex.data;

import bibtex.data.name.NameEquals;
import bibtex.data.record.RecordStorage;
import bibtex.data.string.constant.StringStorage;
import bibtex.syntax.Categories;
import bibtex.syntax.Fields;
import data.operations.IDataStorage;

import java.util.*;

import static bibtex.syntax.Fields.author;
import static bibtex.syntax.Fields.editor;

/**
 * Container class for storing list of {@link bibtex.data.record.RecordStorage}.
 *
 * Implements {@link data.operations.IDataStorage}.
 *
 * @author dak98
 */
public class DataStorage implements IDataStorage {
    private List<RecordStorage> records = new LinkedList<>();
    private List<StringStorage> stringConstants = new LinkedList<>();

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

    /**
     *
     * @return List of records of a BibTex file.
     */
    public List<RecordStorage> getRecords() {
        return this.records;
    }

    /**
     *
     * @return List of all string constants from the BibTex file.
     */
    public List<StringStorage> getStringConstants() {
        return this.stringConstants;
    }

    /**
     *
     * @param lastName
     * @return List of records written or edited by person with specified lastName.
     */
    public List<RecordStorage> getRecordsByName(String lastName) {
        List listByAuthors = new LinkedList<RecordStorage>();
        NameEquals nameEquals = new NameEquals();
        for (RecordStorage record : records) {
            if ((record.getValueOfField(author) != null && nameEquals.equals(record.getValueOfField(author),lastName)) ||
                 record.getValueOfField(editor) != null && nameEquals.equals(record.getValueOfField(editor), lastName)) {
                listByAuthors.add(record);
            }
        }
        return listByAuthors;
    }

    /**
     *
     * @param category
     * @return List of records with given category.
     */
    public List<RecordStorage> getRecordsByCategory(Categories category) {
        List listByCategory = new LinkedList<RecordStorage>();
        for (RecordStorage record : records) {
            if (record.getCategory().equals(category)) {
                listByCategory.add(record);
            }
        }
        return listByCategory;
    }

    /**
     * Expands string constants contained in other string constants
     * and fields of the records from the BibTex file.
     */
    public void expandRecords() {
        for (StringStorage stringConstant : getStringConstants()) {
            stringConstant.expandString(getStringConstants());
        }
        for (RecordStorage record : getRecords()) {
            record.expandFields(getStringConstants());
        }
    }
}

