package dojo.inaction.ch4;

public interface ClickableJ {
    void click();
    default void showOff() {
        System.out.println("I'm clickable!");
    }
}