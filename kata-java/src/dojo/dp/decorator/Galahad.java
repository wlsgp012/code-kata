package dojo.dp.decorator;

public class Galahad extends Knight {
    public static void main(String[] args) {
        Knight knight = new KnightWithAdditionalHp(new KnightWithPowerArmor(new Galahad()));
    }
}
