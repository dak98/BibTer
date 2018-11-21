package data_operations;

public interface IDataChecker {
    public boolean check(String dataToCheck);
    default boolean isNull(String dataToCheck) {
        if (dataToCheck == null) {
            return false;
        } else {
            return true;
        }
    }
}
