package runtime.command.interpreter;

/**
 * Enumerators for all possible commands for
 * displaying parts of the BibTex file.
 *
 * @author dak98
 */
public enum RuntimeCommands {
    listAll,
    listAuthors,
    listCategories;

    /**
     *
     * @param command
     * @return Enum constant matching command.
     *         null otherwise.
     */
    public static RuntimeCommands toEnum(String command) {
        for (RuntimeCommands runtimeCommand : RuntimeCommands.values()) {
            if (runtimeCommand.toString().equals(command)) {
                return runtimeCommand;
            }
        }
        return null;
    }
}
