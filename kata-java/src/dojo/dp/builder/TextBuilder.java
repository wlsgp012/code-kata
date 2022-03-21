package dojo.dp.builder;

public class TextBuilder implements Builder {
    private StringBuffer buffer = new StringBuffer();

    @Override
    public void makeTitle(String title) {
        buffer.append("========================" + System.lineSeparator());
        buffer.append(title);
        buffer.append(System.lineSeparator());
    }

    @Override
    public void makeString(String str) {
        buffer.append("@" + str + System.lineSeparator());
        buffer.append(System.lineSeparator());
    }

    @Override
    public void close() {
        buffer.append("========================" + System.lineSeparator());
    }

    public String getResult() {
        return buffer.toString();
    }
}
