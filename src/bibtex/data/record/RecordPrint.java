package bibtex.data.record;

import bibtex.syntax.Fields;
import data.operations.IDataPrint;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static sun.swing.MenuItemLayoutHelper.max;

public class RecordPrint implements IDataPrint<RecordStorage> {
    private final int lengthBias = 7;

    /**
     * Prints a specified record.
     *
     * @param record
     * @param borderSymbol
     *         Symbol of the border of output.
     */
    @Override
    public void print(RecordStorage record, Character borderSymbol) {
        record = (RecordStorage) record;
        int maxFieldNameLength = getMaxFieldNameLength((Set<Fields>) record.getFields().keySet());
        maxFieldNameLength = max(maxFieldNameLength, record.getCategory().toString().length());
        int maxFieldValueLength = getMaxFieldValueLength(new HashSet<String>(record.getFields().values()));

        int maxLength = maxFieldNameLength + maxFieldValueLength;

        printSeparatorLine(borderSymbol, maxLength + lengthBias);

        System.out.print(borderSymbol + " " + record.getCategory().toString());

        printSpaces(maxLength + lengthBias - 3 - record.getCategory().toString().length());
        System.out.println(borderSymbol);

        for (int i = 0; i < maxLength + lengthBias; i++) {
            System.out.print(borderSymbol);
        }
        System.out.println("");
        for (Map.Entry<Fields,String> entry : (Set<Map.Entry<Fields,String>>) record.getFields().entrySet()) {
            System.out.print(borderSymbol + " " + entry.getKey().toString());

            printSpaces(maxLength + lengthBias - 6 - maxFieldValueLength - entry.getKey().toString().length());

            System.out.print(borderSymbol + " " + entry.getValue());

            printSpaces(maxLength + lengthBias - 6 - maxFieldNameLength - entry.getValue().length());

            System.out.println(borderSymbol);

            printSeparatorLine(borderSymbol, maxLength + lengthBias);
        }
    }
    /**
     *
     * @param fieldsNamesSet
     *          Set of record's Fields enum constants.
     * @return Max String length of record's Fields enum constants.
     */
    private int getMaxFieldNameLength(Set<Fields> fieldsNamesSet) {
        int maxFieldNameLength = 0;
        for (Fields field : fieldsNamesSet) {
            if (maxFieldNameLength < field.toString().length()) {
                maxFieldNameLength = field.toString().length();
            }
        }
        return maxFieldNameLength;
    }

    /**
     *
     * @param fieldsValuesSet
     *          Set of record's fields values.
     * @return Max length of record's fields values.
     */
    private int getMaxFieldValueLength(Set<String> fieldsValuesSet) {
        int maxFieldValueLength = 0;
        for (String fieldValue : fieldsValuesSet) {
            if (maxFieldValueLength < fieldValue.length()) {
                maxFieldValueLength = fieldValue.length();
            }
        }
        return maxFieldValueLength;
    }

    /**
     * Prints a line of borderSymbols of specified length and
     * moves to a new line.
     *
     * @param borderSymbol
     * @param length
     */
    private void printSeparatorLine(Character borderSymbol, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(borderSymbol);
        }
        System.out.println("");
    }

    /**
     * Prints specified number of spaces.
     *
     * @param numberOfSpaces
     */
    private void printSpaces(int numberOfSpaces) {
        for (int i = 0; i < numberOfSpaces; i++) {
            System.out.print(" ");
        }
    }
}
