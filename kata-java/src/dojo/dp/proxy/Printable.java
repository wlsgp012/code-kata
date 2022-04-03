package dojo.dp.proxy;

public interface Printable {
    String getPrinterName();

    void setPrinterName(String name);

    void print(String text);
}
