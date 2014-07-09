package matchmaker;

import java.util.*;

class NodeComparer implements Comparator<Node> {

    public int compare(Node o1, Node o2) {
        int x = o1.getSize();
        int y = o2.getSize();
        if (x > y) return 1;
        else if (x == y) return 0;
        else return -1;
    }

}
