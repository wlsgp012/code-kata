package dojo.dp.memento;

import java.awt.*;

// Originator
public class TextBox {
    private String text = "";
    private int width = 100;
    private Color textColor = Color.BLACK;

    public void type(String s){
        text += s;
    }

    public Memento save(){
        return new Memento(text);
    }

    public void restore(Memento memento){
        this.text = memento.getText();
    }

    @Override
    public String toString() {
        return "TextBox{" +
                "text='" + text + '\'' +
                '}';
    }
}
