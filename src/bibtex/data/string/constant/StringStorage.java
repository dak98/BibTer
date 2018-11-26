package bibtex.data.string.constant;

import data.operations.IDataStorage;

import java.util.HashMap;
import java.util.Map;

public class StringStorage implements IDataStorage<StringStorage> {
    private Map stringConstantMap = new HashMap<String, String>();

    @Override
    public StringStorage get() {
        return this;
    }

    public void addStringConstant(String key, String value) {
        stringConstantMap.put(key,value);
    }

}
