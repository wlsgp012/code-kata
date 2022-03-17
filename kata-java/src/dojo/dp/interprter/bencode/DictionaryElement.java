package dojo.dp.interprter.bencode;

import java.util.Map;
import java.util.stream.Collectors;

public class DictionaryElement implements BencodeElement {
    private Map<StringElement, BencodeElement> map;

    public DictionaryElement(Map<StringElement, BencodeElement> map) {
        this.map = map;
    }

    @Override
    public String interpret() {
        return map.entrySet().stream().map(kv -> kv.getKey().interpret() + kv.getValue().interpret()).collect(Collectors.joining("", "d", "e"));
    }
}
