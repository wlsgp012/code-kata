package dojo.dp.proxy;

import java.util.concurrent.TimeUnit;

public class Printer implements Printable {
    private String name;

    public Printer(String name) {
        this.name = name;
        heavyJob("Initiating Printer instance");
    }

    private void heavyJob(String msg) {
        System.out.println(msg);
        for (int i = 0; i < 5; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(".");
        }
        System.out.println("Finished");
    }

    @Override
    public String getPrinterName() {
        return name;
    }

    @Override
    public void setPrinterName(String name) {
        this.name = name;
    }

    @Override
    public void print(String text) {
        System.out.println("===" + name + "===");
        System.out.println(text);
    }
}
