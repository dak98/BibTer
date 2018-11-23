package data.operations;

import java.util.List;

public interface IDataParser<T> {
    public List<T> parse(StringBuilder data);
    public IDataStorage store(List<T> dataToStore);
}
