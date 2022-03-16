package dojo.dp.observer;

public class BlockObserver implements Observer {
    @Override
    public void update(User u) {
        System.out.println("block db user : " + u);
    }
}
