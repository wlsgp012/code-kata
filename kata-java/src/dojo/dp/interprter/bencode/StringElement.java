package dojo.dp.interprter.bencode;

public class StringElement implements BencodeElement {
    private String value;

    public StringElement(String value) {
        this.value = value;
    }

    @Override
    public String interpret() {
        return value.length() + ":" + value;
    }
}
