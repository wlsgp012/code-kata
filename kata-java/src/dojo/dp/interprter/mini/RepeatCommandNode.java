package dojo.dp.interprter.mini;

// <repeat command> ::= repeat <number> <command list>
public class RepeatCommandNode implements Node {
    private int number;
    private Node commandListNode;

    @Override
    public void parse(Context context) {
        context.skipToken("repeat");
        number = context.currentNumber();
        context.nextToken();
        commandListNode = new CommandListNode();
        commandListNode.parse(context);
    }

    public String toString() {
        return "[repeat " + number + " " + commandListNode + "]";
    }
}
