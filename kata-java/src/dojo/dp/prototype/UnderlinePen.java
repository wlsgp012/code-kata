package dojo.dp.prototype;

import dojo.dp.prototype.framework.Product;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UnderlinePen implements Product {
    private String delimiter;

    public UnderlinePen(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public void use(String s) {
        System.out.println("\"" + s + "\"");
        System.out.println(IntStream.range(0, s.length() * 2).mapToObj(i -> delimiter).collect(Collectors.joining()));
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
