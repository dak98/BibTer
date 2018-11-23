package data.operations;

import java.util.List;

public interface IDataParser<A,B> {
    public List<A> parse(StringBuilder dataToParse);
    public IDataStorage store(List<A> dataToStore);
}
