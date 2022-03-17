package dojo.dp.interprter.bencode;

public class IntegerElement implements BencodeElement {
    private int value;

    public IntegerElement(int value) {
        this.value = value;
    }

    @Override
    public String interpret() {
        return "i" + value + "e";
    }
}
