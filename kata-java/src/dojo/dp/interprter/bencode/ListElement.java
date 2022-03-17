package dojo.dp.interprter.bencode;

import java.util.List;
import java.util.stream.Collectors;

public class ListElement implements BencodeElement {
    private List<? extends BencodeElement> list;

    public ListElement(List<? extends BencodeElement> list) {
        this.list = list;
    }

    @Override
    public String interpret() {
        return list.stream().map(e -> e.interpret()).collect(Collectors.joining("", "l", "e"));
    }
}
