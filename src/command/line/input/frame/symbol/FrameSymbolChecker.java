package command.line.input.frame.symbol;

import data.operations.IDataChecker;

public class FrameSymbolChecker implements IDataChecker {
    /**
     *
     * @param dataToCheck
     * @return True if dataToCheck contains a frame symbol.
     *         False otherwise.
     */
    @Override
    public boolean check(String dataToCheck) {
        return !(isNull(dataToCheck) || dataToCheck.equals("") || !isOneCharacterLong(dataToCheck));
    }

    /**
     *
     * @param dataToCheck
     *         data with a frame symbol
     * @return True if there is only one non-white space character in dataToCheck
     *         False otherwise.
     */
    private boolean isOneCharacterLong(String dataToCheck) {
        return ((removeWhiteSpaces(dataToCheck)).length() == 1);
    }

    /**
     *
     * @param word
     * @return word with removed white spaces (?).
     */
    private String removeWhiteSpaces(String word) {
        return word.replaceAll("\\s+","");
    }
}
