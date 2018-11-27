package bibtex.data;

import bibtex.data.record.RecordStorage;
import bibtex.data.string.constant.StringStorage;
import bibtex.syntax.Categories;
import bibtex.syntax.Fields;
import data.operations.IDataStorage;

import java.util.*;

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
     * @param author
     * @return List of records written by given author.
     */
    public List<RecordStorage> getRecordsByAuthor(String author) {
        List listByAuthors = new LinkedList<RecordStorage>();
        for (RecordStorage record : records) {
            if (record.getValueOfField(Fields.author) != null && record.getValueOfField(Fields.author).equals(author)) {
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
    public List<RecordStorage> getRecordsByCategories(Categories category) {
        List listByCategory = new LinkedList<RecordStorage>();
        for (RecordStorage record : records) {
            if (record.getCategory().equals(category)) {
                listByCategory.add(record);
            }
        }
        return listByCategory;
    }
}

