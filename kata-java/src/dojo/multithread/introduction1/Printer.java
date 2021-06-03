package dojo.multithread.introduction1;

public class Printer implements Runnable{
    private String msg;

    public Printer(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.print(msg);
        }
    }

    public static void main(String[] args) {
        new Thread(new Printer("Good!")).start();
        new Thread(new Printer("Nice!")).start();
    }
}
