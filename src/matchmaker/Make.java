package matchmaker;

public interface Make {
    public Graph makeGraph(String file);
    public Room[] makeRooms(String file);
}
