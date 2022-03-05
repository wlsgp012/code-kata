package dojo.dp.visitor.yuki;

import java.util.Iterator;

public abstract class Entry implements Element{
    public abstract String getName();

    public abstract int getSize();

    public Entry add(Entry entry) {
        throw new IllegalStateException("add exception");
    }

    public Iterator iterator(){
        throw new IllegalStateException("iterator exception");
    }

    @Override
    public String toString() {
        return getName() + " (" + getSize() + ")";
    }
}
