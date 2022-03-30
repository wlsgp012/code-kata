package dojo.dp.abstractfactory;

public class UndergroundLevelFactory implements LevelFactory {
    @Override
    public Wall buildWall() {
        return new StoneWall();
    }

    @Override
    public Back buildBack() {
        return new EarthBack();
    }

    @Override
    public Enemy buildEnemy() {
        return new WormScout();
    }
}
