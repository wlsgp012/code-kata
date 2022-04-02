package dojo.dp.decorator;

public class Knight {
    protected int hp;
    private Knight decorated;

    public Knight() {
    }

    public Knight(Knight decorated) {
        this.decorated = decorated;
    }

    public void attackWithSword() {
        if (decorated != null) decorated.attackWithSword();
    }

    public void attackWithBow() {
        if (decorated != null) decorated.attackWithBow();
    }

    public void blockWithShield() {
        if (decorated != null) decorated.blockWithShield();
    }
}
