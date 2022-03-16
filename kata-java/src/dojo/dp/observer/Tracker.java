package dojo.dp.observer;

import java.util.HashSet;
import java.util.Set;

public class Tracker {
    private Set<Observer> observers = new HashSet<>();

    public void add(Observer o) {
        observers.add(o);
    }

    public void update(User u) {
        observers.forEach(observer -> observer.update(u));
    }
}
