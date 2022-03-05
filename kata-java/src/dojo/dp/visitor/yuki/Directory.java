package dojo.dp.visitor.yuki;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Directory extends Entry {
    private String name;
    private List dir = new ArrayList();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return dir.stream().mapToInt(i -> ((Entry) i).getSize()).sum();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public Entry add(Entry entry) {
        dir.add(entry);
        return this;
    }

    @Override
    public Iterator iterator() {
        return dir.iterator();
    }
}
