package bibtex.data.record;

import bibtex.syntax.Categories;
import bibtex.syntax.Fields;
import bibtex.syntax.FieldsOfCategory;
import bibtex.syntax.ToEnumConverter;
import data.operations.IDataChecker;
import data.operations.IDataParser;
import utility.LineCounter;

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
public class RecordParser implements IDataParser {
    /**
     * Parses an individual record to pick up category, key and fields.
     *
     * @param dataToParse
     *          String with a individual record.
     *          Should start with category lastName and end with '}' sign.
     * @return Instance of RecordStorage representing an individual record or
     *         null if category lastName is not in {@link Categories}.
     * @throws NullPointerException
     *          dataToParse is null.
     * @throws IndexOutOfBoundsException
     *          No record's key was found.
     * @throws IllegalArgumentException
     *          Field's lastName was not correct.
     * @throws UnsupportedOperationException
     *          There weren't enough obligatory fields for category.
     */
    public RecordStorage parse(String dataToParse) {
        LineCounter lineCounter = new LineCounter();
        int startLine = lineCounter.getNumberOfLines();

        /* Storage for current record */
        RecordStorage recordStorage = new RecordStorage();

        IDataChecker recordChecker = new RecordChecker();
        if (recordChecker.check(dataToParse)) {
            dataToParse = dataToParse.substring(0,dataToParse.indexOf('}') - 3);
            /* Get category of a record */
            recordStorage.setCategory(getCategory(dataToParse));
            if (!isValidCategory(recordStorage.getCategory())) {
                return null;
            }
            /* Get key of a record */
            recordStorage.setKey(getKey(dataToParse = dataToParse.substring(dataToParse.indexOf('{') + 1)));
            /* Split by comma */
            StringTokenizer stringTokenizer = new StringTokenizer(dataToParse.substring(dataToParse.indexOf('@') + 1), "@");
            /* Checking if fields meet requirements of category */
            FieldsOfCategory fieldsOfCategory = new FieldsOfCategory();
            List currentListOfObligatoryFields = new LinkedList<Fields>();
            /* Get fields of the record */
            while (stringTokenizer.hasMoreTokens()) {
                lineCounter.increment();
                String token = moveToFirstChar(stringTokenizer.nextToken());
                Fields field = getNextField(token);
                token = moveToFirstChar(token.substring(token.indexOf('=') + 1));
                String value = getNextFieldValue(token + ',');
                /* If field is not ignored in a category */
                if (fieldsOfCategory.isFieldOfCategory(recordStorage.getCategory(), field)) {
                    recordStorage.addField(field, value);
                    if (fieldsOfCategory.isObligatory(recordStorage.getCategory(), field)) {
                        currentListOfObligatoryFields.add(field);
                    }
                }
            }
            /* Check if record has all the obligatory fields */
            List<Fields> listOfObligatoryFields = new LinkedList(fieldsOfCategory.getObligatoryFields(recordStorage.getCategory()));
            listOfObligatoryFields.removeAll(currentListOfObligatoryFields);
            /* If record has all the fields of a category */
            if (listOfObligatoryFields.isEmpty()) {
                return recordStorage;
            } else {
                throw new UnsupportedOperationException("Missing " +
                        reportMissingObligatoryFields(listOfObligatoryFields) +
                        "from " + recordStorage.getCategory() + " in the record starting at line " +
                        startLine + ".");
            }
        }
        return recordStorage;
    }

    /**
     * Parses data to get category lastName of a record.
     *
     * It uses {@link String#toLowerCase()} with default locale.
     *
     * @param dataToParse
     *          String to get category lastName from.
     *          It must start with char sequence to be checked and
     *          end with '{' sign.
     * @return Category lastName of a record or
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
     *
     * @param category
     * @return True if category is not null.
     *         False otherwise.
     */
    private boolean isValidCategory(Categories category) {
        return (category != null);
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
    private String getKey(String dataToParse){
        LineCounter lineCounter = new LineCounter();

        int start = 0;
        int end = dataToParse.indexOf(',');

        if (start >= end) {
            throw new IndexOutOfBoundsException("No key found in a record at line " + lineCounter.getNumberOfLines() + ".");
        }
        try {
            dataToParse = dataToParse.substring(start, end);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No key found in a record at line " + lineCounter.getNumberOfLines() + ".");
        }

        return dataToParse;
    }

    /**
     * Parses data to get lastName of a next field.
     *
     * It uses {@link String#toLowerCase()} with default locale.
     *
     * @param dataToParse
                String to get Field's enum constant from.
     *          It must start with char sequence to be checked and
     *          end with with a space.
     * @return Name of a next field.
     * @throws IllegalArgumentException
     *          Illegal field lastName.
     */
    private Fields getNextField(String dataToParse) {
        int start = 0;
        int end = dataToParse.indexOf(' ');

        ToEnumConverter converter = new ToEnumConverter();
        Fields field = null;
        try {
            field = converter.toField(dataToParse.substring(start, end).toLowerCase());
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            LineCounter lineCounter = new LineCounter();
            throw new IllegalArgumentException("Illegal field name on line " + lineCounter.getNumberOfLines() + ".");
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
        int end = dataToParse.indexOf(',');

        try {
            dataToParse = dataToParse.substring(start, end);
        } catch (IndexOutOfBoundsException e) {
            LineCounter lineCounter = new LineCounter();
            throw new IndexOutOfBoundsException("No field value found on line " + lineCounter.getNumberOfLines() + ".");
        }
        return dataToParse;
    }

    /**
     *
     * @param data
     * @return Substring of data which starts with a char type element.
     */
    private String moveToFirstChar(String data) {
        while (Character.isWhitespace(data.charAt(0))) {
            data = data.substring(1);
        }
        return data;
    }

    /**
     *
     * @param listOfMissingFields
     *         List of missing obligatory fields.
     * @return String with names of missing obligatory fields.
     */
    private String reportMissingObligatoryFields(List<Fields> listOfMissingFields) {
        StringBuilder missingFields = new StringBuilder();
        for (Fields field : listOfMissingFields) {
            missingFields.append(field).append(", ");
        }
        return missingFields.toString();
    }
}
