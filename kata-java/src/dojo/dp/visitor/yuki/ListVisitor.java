package dojo.dp.visitor.yuki;

import java.util.stream.StreamSupport;

public class ListVisitor implements Visitor {
    private String currentDir = "";

    @Override
    public void visit(File file) {
        System.out.println(currentDir + "/" + file);
    }

    @Override
    public void visit(Directory directory) {
        System.out.println(currentDir + "/" + directory);
        String saveDir = currentDir;
        currentDir = currentDir + "/" + directory.getName();
        Iterable iterable = () -> directory.iterator();
        StreamSupport.stream(iterable.spliterator(), false).forEach(i -> {
            var x = (Entry) i;
            x.accept(this);
        });
        currentDir = saveDir;
    }
}
