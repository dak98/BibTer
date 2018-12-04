package data.operations;

public interface IDataChecker {
    public boolean check(String dataToCheck);
    default boolean isNull(String dataToCheck) {
        return (dataToCheck == null);
    }
}
