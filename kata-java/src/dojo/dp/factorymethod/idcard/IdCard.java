package dojo.dp.factorymethod.idcard;

import dojo.dp.factorymethod.framework.Product;

public class IdCard implements Product {
    private String owner;

    IdCard(String owner) {
        System.out.println("made of " + owner);
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println("use of " + owner);
    }

    public String getOwner() {
        return owner;
    }
}
