package dojo.dp.interprter.mini;

import java.util.ArrayList;
import java.util.List;

// <command list> ::= <command>* end
public class CommandListNode implements Node {
    private final List list = new ArrayList();

    @Override
    public void parse(Context context) {
        while (true) {
            if (context.getCurrentToken() == null) {
                throw new RuntimeException("Missing 'end");
            } else if (context.getCurrentToken().equals("end")) {
                context.skipToken("end");
                break;
            } else {
                Node commandNode = new CommandNode();
                commandNode.parse(context);
                list.add(commandNode);
            }
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
