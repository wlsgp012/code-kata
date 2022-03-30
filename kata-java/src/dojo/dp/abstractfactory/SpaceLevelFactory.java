package dojo.dp.abstractfactory;

public class SpaceLevelFactory implements LevelFactory {
    @Override
    public Wall buildWall() {
        return new PlasmaWall();
    }

    @Override
    public Back buildBack() {
        return new StarsBack();
    }

    @Override
    public Enemy buildEnemy() {
        return new UfoSoldier();
    }
}
