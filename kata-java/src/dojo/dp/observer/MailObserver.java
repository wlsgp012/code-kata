package dojo.dp.observer;

public class MailObserver implements Observer {
    @Override
    public void update(User u) {
        System.out.println("send to fbi user: " + u);
    }
}
