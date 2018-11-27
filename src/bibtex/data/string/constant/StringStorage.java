package bibtex.data.string.constant;

import data.operations.IDataStorage;

import java.util.HashMap;
import java.util.Map;

public class StringStorage implements IDataStorage<StringStorage> {
    private Map stringConstantMap = new HashMap<String, String>();

    public void addStringConstant(String key, String value) {
        stringConstantMap.put(key,value);
    }

}
