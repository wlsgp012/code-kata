package dojo.dp.factorymethod.idcard;

import dojo.dp.factorymethod.framework.Factory;
import dojo.dp.factorymethod.framework.Product;

import java.util.ArrayList;
import java.util.List;

public class IdCardFactory extends Factory {
    private List<String> owners = new ArrayList<>();

    @Override
    protected Product createProduct(String owner) {
        return new IdCard(owner);
    }

    @Override
    protected void registerProduct(Product product) {
        owners.add(((IdCard) product).getOwner());
    }

    public List<String> getOwners() {
        return owners;
    }
}
