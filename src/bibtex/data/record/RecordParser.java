package bibtex.data.record;

import bibtex.syntax.Categories;
import bibtex.syntax.Fields;
import bibtex.syntax.FieldsOfCategory;
import bibtex.syntax.ToEnumConverter;
import data.operations.IDataParser;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

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
     * Parses an individual record to pick up category, key and fields.
     *
     * @param dataToParse
     *          String with a individual record.
     *          Should start with category name and end with '}' sign.
     * @return Instance of RecordStorage representing an individual record or
     *         null if category name is not in {@link bibtex.syntax.Categories}.
     * @throws NullPointerException
     *          dataToParse is null.
     * @throws IndexOutOfBoundsException
     *          No record's key was found.
     * @throws IllegalArgumentException
     *          Field's name or record itself was not correct.
     * @throws UnsupportedOperationException
     *          There weren't enough obligatory fields for category.
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

        if (category == null) {
            return null;
        }

        dataToParse = dataToParse.substring(dataToParse.indexOf('{') + 1);
        StringTokenizer stringTokenizer = new StringTokenizer(dataToParse, ",");

        String key = getKey(dataToParse);

        RecordStorage recordStorage = new RecordStorage(category, key);

        FieldsOfCategory fieldsOfCategory = new FieldsOfCategory();
        List listOfObligatoryFields = new LinkedList<Fields>();
        String token = stringTokenizer.nextToken();
        while (!((token = moveToFirstChar(stringTokenizer.nextToken())).indexOf('}') == 0)) {
            Fields field = getNextField(token);

            token = token.substring(token.indexOf('=') + 3);

            String value = getNextFieldValue(token);

            if (fieldsOfCategory.isFieldOfCategory(category, field)) {
                recordStorage.addField(field, value);
                if (fieldsOfCategory.isObligatory(category, field)) {
                    listOfObligatoryFields.add(field);
                }
            }
        }

        if (listOfObligatoryFields.containsAll(fieldsOfCategory.getObligatoryFields(category))) {
            return recordStorage;
        } else {
            throw new UnsupportedOperationException("There weren't enough obligatory fields for category");
        }


    }

    /**
     * Parses data to get category name of a record.
     *
     * It uses {@link String#toLowerCase()} with default locale.
     *
     * @param dataToParse
     *          String to get category name from.
     *          It must start with char sequence to be checked and
     *          end with '{' sign.
     * @return Category name of a record or
     *         null if it is not in {@link bibtex.syntax.Categories}.
     */
    private Categories getCategory(String dataToParse) {
        int start = 0;
        int end = dataToParse.indexOf('{');

        ToEnumConverter converter = new ToEnumConverter();
        Categories category;
        try {
            category = converter.toCategory(dataToParse.substring(start, end).toLowerCase());
        } catch(IllegalArgumentException | IndexOutOfBoundsException e) {
            category = null;
        }

        return category;
    }

    /**
     * Parses data to get key of a record.
     *
     * @param dataToParse
     *          String to get record's key from.
     *          It must start with char sequence to be checked and
     *          end with ',' sign.
     * @return Key of a record.
     * @throws IndexOutOfBoundsException
     *          No key in a record.
     */
    private String getKey(String dataToParse) {
        int start = 0;
        int end = dataToParse.indexOf(',');

        if (start >= end) {
            throw new IndexOutOfBoundsException();
        }

        try {
            dataToParse = dataToParse.substring(start, end);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No key found in a record.");
        }

        return dataToParse;
    }

    /**
     * Parses data to get name of a next field.
     *
     * It uses {@link String#toLowerCase()} with default locale.
     *
     * @param dataToParse
                String to get Field's enum constant from.
     *          It must start with char sequence to be checked and
     *          end with with a space.
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
                String to get field's value from.
     *          It must start with char sequence to be checked and
     *          end with ',' sign.
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
            throw new IndexOutOfBoundsException("No value of a particular field found in a record.");
        }

        return dataToParse;
    }

    /**
     *
     * @param data
     * @return Substring of data which starts with a char type element.
     */
    private String moveToFirstChar(String data) {
        while (data.indexOf(' ') == 0) {
            data = data.substring(1);
        }

        return data;
    }
}
