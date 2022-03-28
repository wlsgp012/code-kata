package dojo.dp.composite;

import java.util.List;

public abstract class Block {
    void addBlock(Block b) {

    }

    List<Block> getChildren() {
        return null;
    }

    void render() {
        System.out.println("render");
    }
}
