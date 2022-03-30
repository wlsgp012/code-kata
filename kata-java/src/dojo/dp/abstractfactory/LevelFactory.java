package dojo.dp.abstractfactory;

public interface LevelFactory {
    Wall buildWall();

    Back buildBack();

    Enemy buildEnemy();
}
