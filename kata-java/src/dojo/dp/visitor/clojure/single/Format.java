package dojo.dp.visitor.clojure.single;

abstract class Format {
}

class PDF extends Format {
}

class XML extends Format {
}

abstract class Item {
    void export(Format f) {
        throw new IllegalCallerException("No way");
    }

    abstract void export(PDF pdf);

    abstract void export(XML xml);
}

class Message extends Item {
    @Override
    void export(PDF f) {
        PDFExporter.export(this);
    }

    @Override
    void export(XML xml) {
        XMLExporter.export(this);
    }
}

class Activity extends Item {
    @Override
    void export(PDF pdf) {
        PDFExporter.export(this);
    }

    @Override
    void export(XML xml) {
        XMLExporter.export(this);
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
//        PDF f = new PDF();
        Format f = new PDF();
        i.export(f);
    }
}