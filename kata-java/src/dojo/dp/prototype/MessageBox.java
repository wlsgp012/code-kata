package dojo.dp.prototype;

import dojo.dp.prototype.framework.Product;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MessageBox implements Product {
    private String delimiter;

    public MessageBox(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public void use(String s) {
        String delimiters = IntStream.range(0, s.length() * 2).mapToObj(i -> delimiter).collect(Collectors.joining());
        System.out.println(delimiters);
        System.out.println(delimiter + " " + s + " " + delimiter);
        System.out.println(delimiters);
    }

    @Override
    public Product createClone() {
        try {
            return (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
