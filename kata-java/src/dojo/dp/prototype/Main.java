package dojo.dp.prototype;

import dojo.dp.prototype.framework.Manager;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.register("strong message", new UnderlinePen("~"));
        manager.register("warning box", new MessageBox("*"));
        manager.register("slash box", new MessageBox("/"));

        String s = "Hello, World";
        manager.create("strong message").use(s);
        manager.create("warning box").use(s);
        manager.create("slash box").use(s);
    }
}
