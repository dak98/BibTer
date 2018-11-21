package data_operations;

public interface IDataParser<T> {
    public T[] parse(String commandLineArg);
    public void store(T[] dataToStore);
}
