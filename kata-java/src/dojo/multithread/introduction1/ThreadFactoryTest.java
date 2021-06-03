package dojo.multithread.introduction1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryTest {

    public static void main(String[] args) {
        ThreadFactory factory = Executors.defaultThreadFactory();
        factory.newThread(new Printer("Nice!")).start();
        for (int i = 0; i < 10000; i++) {
            System.out.print("Good!");
        }
    }
}
