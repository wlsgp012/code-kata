package dojo.inaction.ch4;

public interface FocusableJ {
    default void setFocus(Boolean b) {
        var verb = b? "got" : "lost";
        System.out.printf("I %s focus", verb);
        System.out.println();
    }
    default void showOff() {
        System.out.println("I'm focusable!");
    }
}