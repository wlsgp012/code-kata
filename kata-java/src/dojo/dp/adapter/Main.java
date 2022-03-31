package dojo.dp.adapter;

public class Main {
    public static void main(String[] args) {
        PrintBanner printBanner = new PrintBanner(new Banner("Hello"));
        printBanner.printStrong();
        printBanner.printWeak();
    }
}
