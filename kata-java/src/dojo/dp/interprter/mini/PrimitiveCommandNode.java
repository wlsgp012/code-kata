package dojo.dp.interprter.mini;

// <primitive command> ::= go | right | left
public class PrimitiveCommandNode implements Node {
    private String name;

    @Override
    public void parse(Context context) {
        name = context.getCurrentToken();
        context.skipToken(name);
        if (!name.equals("go") && !name.equals("right") && !name.equals("left")) {
            throw new RuntimeException(name + " is undefined");
        }
    }

    public String toString() {
        return name;
    }
}
