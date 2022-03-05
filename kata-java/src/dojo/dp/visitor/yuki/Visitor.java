package dojo.dp.visitor.yuki;


public interface Visitor {
    void visit(File file);

    void visit(Directory directory);
}
