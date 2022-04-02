package dojo.dp.decorator;

public class KnightWithPowerArmor extends Knight {
    public KnightWithPowerArmor(Knight decorated) {
        super(decorated);
    }

    @Override
    public void blockWithShield() {
        super.blockWithShield();
        System.out.println("Add Power Armor");
    }
}
