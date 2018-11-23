package bibtex.syntax;

/**
 * Enumerators for all the possible categories available in BibTex.
 *
 * @author dak98
 */
public enum Categories {
    article,
    book,
    inproceedings,
    conference,
    booklet,
    inbook,
    incollection,
    manual,
    masterthesis,
    phdthesis,
    techreport,
    misc,
    unpublished;

    private final int numberOfFields = Fields.values().length;

    private boolean[] obligatory = new boolean[numberOfFields];
    private boolean[] optional = new boolean[numberOfFields];
    private boolean[] ignored = new boolean[numberOfFields];
}
