package matchmaker;

public interface Cost {

    public double eval(Graph graph);
    // room can be null
    public double deltaEval(Graph graph, Node node, Room room);

}
