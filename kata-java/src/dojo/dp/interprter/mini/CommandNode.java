package dojo.dp.interprter.mini;

public class CommandNode implements Node {
    private Node node;

    @Override
    public void parse(Context context) {
        if (context.getCurrentToken().equals("repeat")) {
            node = new RepeatCommandNode();
            node.parse(context);
        } else {
            node = new PrimitiveCommandNode();
            node.parse(context);
        }
    }

    public String toString() {
        return node.toString();
    }
}
