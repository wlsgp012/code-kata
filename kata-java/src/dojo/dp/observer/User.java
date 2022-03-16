package dojo.dp.observer;

public class User {
    private String name;
    private double balance;
    private Tracker tracker;

    public User() {
        initTracker();
    }

    private void initTracker() {
        tracker = new Tracker();
        tracker.add(new MailObserver());
        tracker.add(new BlockObserver());
    }

    public void addMoney(double amount) {
        balance += amount;
        if (amount > 100) {
            tracker.update(this);
        }
    }
}
