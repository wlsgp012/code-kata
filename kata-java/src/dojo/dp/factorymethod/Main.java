package dojo.dp.factorymethod;

import dojo.dp.factorymethod.framework.Factory;
import dojo.dp.factorymethod.framework.Product;
import dojo.dp.factorymethod.idcard.IdCardFactory;

public class Main {
    public static void main(String[] args) {
        Factory factory = new IdCardFactory();
        Product tom = factory.create("tom");
        Product david = factory.create("david");
        Product mary = factory.create("mary");
        tom.use();
        david.use();
        mary.use();
    }
}
