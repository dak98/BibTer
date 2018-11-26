package bibtex.syntax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static bibtex.syntax.Fields.*;

/**
 * Contains fields and methods responsible for storing and
 * checking information about fields of given category.
 *
 * @author dak98
 */
public class FieldsOfCategory {
    private final int numberOfCategories = Categories.values().length;
    private List<List<Fields>> obligatory = new ArrayList<List<Fields>>(numberOfCategories);
    private List<List<Fields>> optional = new ArrayList<List<Fields>>(numberOfCategories);

    /**
     * Default constructor. Initialises
     * - {@link bibtex.syntax.FieldsOfCategory#obligatory} and
     * - {@link bibtex.syntax.FieldsOfCategory#optional}.
     */
    public FieldsOfCategory() {
        // article
        obligatory.add(Arrays.asList(author, title, journal, year));
        optional.add(Arrays.asList(volume, number, pages, month, note, key));
        // book
        obligatory.add(Arrays.asList(author, editor, title, publisher, year));
        optional.add(Arrays.asList(volume, series, address, edition, month, note, key));
        // inproceedings
        obligatory.add(Arrays.asList(author, title, booktitle, year));
        optional.add(Arrays.asList(editor, volume, number, series, pages, address, month, organization, publisher, note, key));
        // conference
        obligatory.add(Arrays.asList(author, title, booktitle, year));
        optional.add(Arrays.asList(editor, volume, number, series, pages, address, month, organization, publisher, note, key));
        // booklet
        obligatory.add(Arrays.asList(title));
        optional.add(Arrays.asList(author, howpublished, address, month, year, note, key));
        // inbook
        obligatory.add(Arrays.asList(author, editor, title, chapter, pages, publisher, year));
        optional.add(Arrays.asList(volume, number, series, type, address, edition, month, note, key));
        // incollection
        obligatory.add(Arrays.asList(author, title, booktitle, publisher, year));
        optional.add(Arrays.asList(editor, volume, number, series, type, chapter, pages, address, edition, month, note, key));
        // manual
        obligatory.add(Arrays.asList(title));
        optional.add(Arrays.asList(author, organization, address, edition, month, year, note, key));
        // masterthesis
        obligatory.add(Arrays.asList(author, title, school, year));
        optional.add(Arrays.asList(type, address, month, note, key));
        // phdthesis
        obligatory.add(Arrays.asList(author, title, school, year));
        optional.add(Arrays.asList(type, address, month, note, key));
        // techreport
        obligatory.add(Arrays.asList(author, title, institution, year));
        optional.add(Arrays.asList(editor, volume, number, series, address, month, organization, publisher, note, key));
        // misc
        obligatory.add(Arrays.asList());
        optional.add(Arrays.asList(author, title, howpublished, month, year, note, key));
        // unpublished
        obligatory.add(Arrays.asList(author, title, note));
        optional.add(Arrays.asList(month, year, key));
    }

    /**
     *
     * @param category
     * @param fieldToCheck
     * @return True if fieldToCheck is obligatory for category.
     *         False otherwise.
     */
    public boolean isObligatory(Categories category, Fields fieldToCheck) {
        for (Fields field : obligatory.get(category.ordinal())) {
            if (field.equals(fieldToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param category
     * @param fieldToCheck
     * @return True if fieldToCheck is optional for category.
     *         False otherwise.
     */
    public boolean isOptional(Categories category, Fields fieldToCheck) {
        for (Fields field : optional.get(category.ordinal())) {
            if (field.equals(fieldToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param category
     * @param fieldToCheck
     * @return True if fieldToCheck is obligatory of optional for category.
     *         False otherwise.
     */
    public boolean isFieldOfCategory(Categories category, Fields fieldToCheck) {
        return (isObligatory(category, fieldToCheck) || isOptional(category, fieldToCheck));
    }

    /**
     *
     * @param category
     * @return List of categories obligatory fields.
     */
    public List getObligatoryFields(Categories category) {
        return obligatory.get(category.ordinal());
    }

}
