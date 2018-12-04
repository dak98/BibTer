package command.line.input.lastname;

import data.operations.IDataStorage;

/**
 * Stores a single last name given through
 * command line.
 *
 * @author dak98
 */
public class LastNameStorage implements IDataStorage {
    private String lastName;

    /**
     * Defualt constructor.
     * @param name
     */
    public LastNameStorage(String name) {
        this.lastName = name;
    }

    /**
     *
     * @return Last name stored in this instance.
     */
    public String getLastName() {
        return lastName;
    }
}
