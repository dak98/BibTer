package bibtex.syntax;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToEnumConverterTest {

    @Test
    void shouldBeRecognizedAsCategory() {
        ToEnumConverter converter = new ToEnumConverter();
        assertTrue(converter.isCategory("aRticLe"));
        assertTrue(converter.isCategory("BooK"));
    }

    @Test
    void shouldNotBeRecognizedAsCategory() {
        ToEnumConverter converter = new ToEnumConverter();
        assertFalse(converter.isCategory("RticLe"));
        assertFalse(converter.isCategory("B0oK"));
    }

    @Test
    void toFieldTest() {
        int length = Fields.values().length;

        String[] fields = new String[length];
        for (int i = 0; i < length; i++) {
            fields[i] = Fields.values()[i].toString();
        }

        ToEnumConverter converter = new ToEnumConverter();

        for (int i = 0; i < length; i++) {
            assertEquals(Fields.values()[i], converter.toField(fields[i]));
        }

        assertThrows(IllegalArgumentException.class, () -> {converter.toField("string");});
    }

    @Test
    void toCategoryTest() {
        int length = Categories.values().length;

        String[] categories = new String[length];
        for (int i = 0; i < length; i++) {
            categories[i] = Categories.values()[i].toString();
        }

        ToEnumConverter converter = new ToEnumConverter();

        for (int i = 0; i < length; i++) {
            assertEquals(Categories.values()[i], converter.toCategory(categories[i]));
        }

        assertThrows(IllegalArgumentException.class, () -> {converter.toCategory("published");});
    }

    @Test
    void toKeyWordTest() {
        int length = KeyWords.values().length;

        String[] keywords = new String[length];
        for (int i = 0; i < length; i++) {
            keywords[i] = KeyWords.values()[i].toString();
        }

        ToEnumConverter converter = new ToEnumConverter();

        for (int i = 0; i < length; i++) {
            assertEquals(KeyWords.values()[i], converter.toKeyWord(keywords[i]));
        }

        assertThrows(IllegalArgumentException.class, () -> {converter.toKeyWord("lastName");});
    }
}