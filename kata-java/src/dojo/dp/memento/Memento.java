package dojo.dp.memento;

public final class Memento {
    private final String text;

    public Memento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
