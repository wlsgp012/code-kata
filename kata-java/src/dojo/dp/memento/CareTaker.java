package dojo.dp.memento;

public class CareTaker {
    public static void main(String[] args) {
        // open browser, init empty textbox
        TextBox textbox = new TextBox();

        textbox.type("Dear, Madonna\n");
        textbox.type("Let me tell you what ");

        System.out.println(textbox.toString());
        Memento checkpoint1 = textbox.save();

        textbox.type("song 'Like A Virgin' is about. ");
        textbox.type("It's all about a girl...");
        System.out.println(textbox.toString());

        // crashed
        textbox = new TextBox();

        textbox.restore(checkpoint1);
        System.out.println(textbox.toString());
    }
}
