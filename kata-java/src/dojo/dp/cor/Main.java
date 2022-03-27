package dojo.dp.cor;

public class Main {
    public static void main(String[] args) {
        NoSupport alice = new NoSupport("Alice");
        LimitSupport bob = new LimitSupport("Bob", 100);
        SpecialSupport charlie = new SpecialSupport("Charlie", 429);
        LimitSupport diana = new LimitSupport("Diana", 200);
        OddSupport elmo = new OddSupport("Elmo");
        LimitSupport fred = new LimitSupport("Fred", 300);

        alice.setNext(bob).setNext(charlie).setNext(diana).setNext(elmo).setNext(fred);

        for (int i = 0; i < 500; i += 33) {
            alice.support(new Trouble(i));
        }
    }
}
