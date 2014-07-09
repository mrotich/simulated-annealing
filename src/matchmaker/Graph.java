package matchmaker;

public interface Graph {
    public Node[] getNodes();
    public boolean adjacent(Node a, Node b);
    public void addNode(Node a);
    public int setRoom(Node node, Room room);
    public int lastChange();
    public void undo(int step);
}
