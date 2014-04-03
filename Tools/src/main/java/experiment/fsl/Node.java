package experiment.fsl;

import experiment.fsl.templet.MapNodeTemplet;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-17
 * Time: 下午4:39
 */

@Data
//@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Node {

    private final MapNodeTemplet nodeTemplet;
    private boolean isDone = false;

    public Node(MapNodeTemplet nodeTemplet) {
        this.nodeTemplet = nodeTemplet;
    }

    static Node createHead() {
        Node head = new Node(null);
        head.setDone(true);
        return head;
    }
}
