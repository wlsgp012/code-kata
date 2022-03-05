package dojo.dp.visitor.clojure.pattern;

abstract class Format {
}

class PDF extends Format {
}

class XML extends Format {
}

interface Visitor {
    void visit(Activity a);
    void visit(Message m);
}

class PDFVisitor implements Visitor {
    @Override
    public void visit(Activity a) {
        PDFExporter.export(a);
    }

    @Override
    public void visit(Message m) {
        PDFExporter.export(m);
    }
}

interface Item {
     void accept(Visitor v);
}

class Message implements Item {
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}

class Activity implements Item {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}

class PDFExporter {
    public static void export(Item item) {
        System.out.println("Pdf Exporter : " + item.getClass().getSimpleName());
    }
}

class XMLExporter {
    public static void export(Item item) {
        System.out.println("Xml Exporter : " + item.getClass().getSimpleName());
    }
}

class Main {
    public static void main(String[] args) {
        Item i = new Activity();
        Visitor v = new PDFVisitor();
        i.accept(v);
    }
}