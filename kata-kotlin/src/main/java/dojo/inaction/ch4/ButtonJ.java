package dojo.inaction.ch4;

public class ButtonJ implements ClickableJ, FocusableJ {
    @Override
    public void click() {
        System.out.println("I was clicked");
    }

    @Override
    public void showOff() {
        ClickableJ.super.showOff();
        FocusableJ.super.showOff();
    }

    public static void main(String[] args) {
        ButtonJ buttonJ = new ButtonJ();
        buttonJ.showOff();
        buttonJ.setFocus(true);
        buttonJ.click();
    }
}


