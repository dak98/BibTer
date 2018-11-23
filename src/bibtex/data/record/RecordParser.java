package bibtex.data.record;

import bibtex.syntax.Categories;
import bibtex.syntax.Fields;
import bibtex.syntax.ToEnumConverter;
import data.operations.IDataParser;

/**
 * Contains methods for parsing individual BibTex's record.
 * Receives data from {@link bibtex.data.DataParser}.
 * Stores data to {@link bibtex.data.record.RecordStorage}.
 *
 * Implements {@link data.operations.IDataParser}.
 *
 * @author dak98
 */
public class RecordParser implements IDataParser<RecordStorage> {
    /**
     * Parses an individual record to pick up category and fields.
     *
     * @param dataToParse
     *          String with the indivual record.
     *          Should start with category name and end with ')' sign.
     * @return Instance of RecordStorage representing an individual record.
     * @throws NullPointerException
     *          dataToParse argument is null.
     * @throws IllegalArgumentException
     *          Category, key, Field's name or record itself was not correct.
     */
    public RecordStorage parse(String dataToParse) {
        if (dataToParse == null) {
            throw new NullPointerException();
        }

        int end = dataToParse.indexOf('}');
        if (end == -1) {
            throw new IllegalArgumentException();
        }

        Categories category = getCategory(dataToParse);

        dataToParse = dataToParse.substring(dataToParse.indexOf('{') + 1);

        String key = getKey(dataToParse);

        RecordStorage recordStorage = new RecordStorage(category, key);

        boolean getFieldName = true;
        Fields field = null;
        String fieldValue = null;
        dataToParse = dataToParse.substring(dataToParse.indexOf(',') + 1);
        end = dataToParse.indexOf('}');
        while (end > 0) {
            if (getFieldName) {
                field = getNextField(dataToParse);
                dataToParse = dataToParse.substring(dataToParse.indexOf('"') + 1);
            } else {
                fieldValue = getNextFieldValue(dataToParse);
                dataToParse = dataToParse.substring(dataToParse.indexOf(',') + 1);
                recordStorage.addField(field, fieldValue);
            }
            getFieldName = !getFieldName;
            end = dataToParse.indexOf('}');
        }

        return recordStorage;
    }

    /**
     * Parses data to get category name of a record.
     *
     * @param dataToParse
     * @return Category name of a record.
     * @throws IllegalArgumentException
     *          Illegal category name
     */
    private Categories getCategory(String dataToParse) {
        int start = 0;
        int end = dataToParse.indexOf('{');

        ToEnumConverter converter = new ToEnumConverter();
        Categories category = null;
        try {
            category = converter.toCategory(dataToParse.substring(start, end).toLowerCase());
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Illegal category name.");
        }

        return category;
    }

    /**
     * Parses data to get key of a record.
     *
     * @param dataToParse
     * @return Key of a record.
     * @throws IndexOutOfBoundsException
     *          No key in a record.
     */
    private String getKey(String dataToParse) {
        int start = 0;
        int end = dataToParse.indexOf(',');

        try {
            dataToParse = dataToParse.substring(start, end);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No key in a record");
        }

        return dataToParse;
    }

    /**
     * Parses data to get name of a next field.
     *
     * @param dataToParse
     * @return Name of a next field.
     * @throws IllegalArgumentException
     *          Illegal field name.
     */
    private Fields getNextField(String dataToParse) {
        int start = 0;
        int end = dataToParse.indexOf(' ');

        ToEnumConverter converter = new ToEnumConverter();
        Fields field = null;
        try {
            field = converter.toField(dataToParse.substring(start, end).toLowerCase());
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Illegal field name.");
        }

        return field;
    }

    /**
     * Parses data to get string value of a next field.
     *
     * @param dataToParse
     * @return String value of a next field
     * @throws IndexOutOfBoundsException
     *          Field value not found.
     */
    private String getNextFieldValue(String dataToParse) {
        int start = 0;
        int end = dataToParse.indexOf('"');

        try {
            dataToParse = dataToParse.substring(start, end);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No key in a record");
        }

        return dataToParse;
    }
}
