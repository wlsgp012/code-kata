package dojo.dp.interprter.mini;

// <program> ::= program <command list>
public class ProgramNode implements Node {
    private Node commandListNode;

    @Override
    public void parse(Context context) {
        context.skipToken("program");
        commandListNode = new CommandListNode();
        commandListNode.parse(context);
    }

    @Override
    public String toString() {
        return "[program " + commandListNode + "]";
    }
}
