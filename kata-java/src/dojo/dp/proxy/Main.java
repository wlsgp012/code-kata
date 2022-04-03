package dojo.dp.proxy;

public class Main {
    public static void main(String[] args) {
        Printable proxy = new PrinterProxy("Alice");
        System.out.println(proxy.getPrinterName());
        proxy.setPrinterName("Driss");
        System.out.println(proxy.getPrinterName());
        proxy.print("Hello World");
    }
}
