package dojo.dp.prototype.framework;

import java.util.HashMap;

public class Manager {
    private HashMap<String, Product> showCase = new HashMap<>();

    public void register(String name, Product prototype){
        showCase.put(name, prototype);
    }

    public Product create(String name){
        Product product = showCase.get(name);
        return product.createClone();
    }
}
