package dojo.dp.strategy;

public interface Strategy {
    Hand nextHand();

    void study(boolean win);
}
