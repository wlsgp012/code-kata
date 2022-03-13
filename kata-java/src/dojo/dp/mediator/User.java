package dojo.dp.mediator;

public class User {
  private String name;
  private Mediator mediator;

  public User(String name, Mediator mediator) {
    this.name = name;
    this.mediator = mediator;
  }

  public void send(String text){
    mediator.sendMessage(this, text);
  }

  public void receive(String text) {
    // do something
  }
}
