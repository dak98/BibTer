package bibtex.data.record;

import data.operations.IDataChecker;

/**
 * Contains classes responsible for checking data
 * passed to {@link bibtex.data.record.RecordParser}.
 *
 * @author dak98
 */
public class RecordChecker implements IDataChecker {
    /**
     * Main check function.
     *
     * @param dataToCheck
     *         Data from {@link bibtex.data.record.RecordParser} to be checked.
     * @return True if checking is successful. False otherwise.
     */
    public boolean check(String dataToCheck) {
        boolean answer = true;
        if (isNull(dataToCheck)) {
            throw new NullPointerException();
        }
        if (!hasClosingRecordBrace(dataToCheck)) {
            answer = false;
        }
        return answer;
    }

    /**
     *
     * @param dataToCheck
     * @return True if dataToCheck has a '}' sign. False otherwise.
     */
    private boolean hasClosingRecordBrace(String dataToCheck) {
        return (dataToCheck.indexOf('}') != -1);
    }
}
