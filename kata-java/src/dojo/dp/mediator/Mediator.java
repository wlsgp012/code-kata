package dojo.dp.mediator;

import java.util.ArrayList;
import java.util.List;

public class Mediator {
    private List<User> users = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
    }

    public void sendMessage(User user, String text){
        users.forEach(u -> u.receive(text));
    }
}
