package dojo.dp.visitor.clojure.single;

interface FormatPrinter {
    void print(Message message);
    void print(Activity activity);
}

class PDF implements FormatPrinter {
    @Override
    public void print(Message message) {
        System.out.println("to pdf -> message : " + message);
    }

    @Override
    public void print(Activity activity) {
        System.out.println("to pdf -> activity : " + activity);
    }
}

class XML implements FormatPrinter {
    @Override
    public void print(Message message) {
        System.out.println("to xml -> message : " + message);
    }

    @Override
    public void print(Activity activity) {
        System.out.println("to xml -> activity : " + activity);
    }
}

interface Item {
    void export(PDF pdf);

    void export(XML xml);
}

class Message implements Item {

    @Override
    public void export(PDF pdf) {
        pdf.print(this);
    }

    @Override
    public void export(XML xml) {
        xml.print(this);
    }
}

class Activity implements Item {
    @Override
    public void export(PDF pdf) {
        pdf.print(this);
    }

    @Override
    public void export(XML xml) {
        xml.print(this);
    }
}


class Main {
    public static void main(String[] args) {
        Item i = new Activity();
        PDF f = new PDF();
//        FormatPrinter f = new PDF();
        i.export(f);
    }
}