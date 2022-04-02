package dojo.dp.decorator;

public class KnightWithAdditionalHp extends Knight {
    public KnightWithAdditionalHp(Knight decorated) {
        super(decorated);
        this.hp += 50;
    }
}
