package bibtex.syntax;

/**
 * Contains methods converting string literals to:
 * - {@link bibtex.syntax.Fields} - {@link bibtex.syntax.ToEnumConverter#toField(String)}
 * - {@link bibtex.syntax.Categories} - {@link bibtex.syntax.ToEnumConverter#toCategory(String)}
 *
 * (Factory design pattern)
 *
 * @author dak98
 */
public class ToEnumConverter {
    /**
     * Converts given String to a Fields' enum constant.
     *
     * @param stringLiteral
     *          String to be converted.
     * @return Fields' enum constant
     * @throws IllegalArgumentException
     *          Argument doesn't match any of the Fields' enum constants.
     */
    public Fields toField(String stringLiteral) throws IllegalArgumentException {
        for (Fields field : Fields.values()) {
            if (stringLiteral.equals(field.toString())) {
                return field;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Converts given String to a Categories' enum constant.
     *
     * @param stringLiteral
     *          String to be converted.
     * @return Categories' enum constant.
     * @throws IllegalArgumentException
     *          Argument doesn't match any of the Categories' enum constants.
     */
    public Categories toCategory(String stringLiteral) throws IllegalArgumentException {
        for (Categories category : Categories.values()) {
            if (stringLiteral.equals(category.toString())) {
                return category;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Converts given String to a KeyWords' enum constant.
     *
     * @param stringLiteral
     *          String to be converted.
     * @return KeyWords' enum constant.
     * @throws IllegalArgumentException
     *          Argument doesn't match any of the KeyWords' enum constants.
     */
    public KeyWords toKeyWord(String stringLiteral) throws IllegalArgumentException {
        for (KeyWords keyword : KeyWords.values()) {
            if (stringLiteral.equals(keyword.toString())) {
                return keyword;
            }
        }
        throw new IllegalArgumentException();
    }
}
