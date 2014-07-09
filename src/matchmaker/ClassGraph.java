package matchmaker;

import java.util.ArrayList;

import matchmaker.Graph;

class ClassGraph implements Graph {

    /* The representation is an adjacency list
     * Graph class has a list of Nodes representing courses
     * Nodes class has a list of adjacent nodes (courses held at same time)
     */
    private ArrayList<Node> courses;
    // Maybe use queue for this instead?
    private ArrayList<Changeset> changes;

    public ClassGraph(Node[] n){
        this.changes = new ArrayList<Changeset>();
        for(int i=0; i<n.length; i++)
            courses.add(n[i]);
    }

    public ClassGraph(){
        courses = new ArrayList<Node>();
    }

    public void printG(){
        for(int i=0; i<courses.size(); i++){
             System.out.printf(" %-12s | %-30s | %-10s \n", courses.get(i).getCourseNumber(), courses.get(i).getTitle(), courses.get(i).getRoom().getName());
        }
    }

    public boolean adjacent (Node a, Node b){
        return a.isNeighbor(b);
    }

    public Node[] getNodes() {
        return courses.toArray(new Node[courses.size()]);
    }

    public void addNode(Node a){
        if(!courses.contains(a))
            courses.add(a);
    }

    // Return index of added change
    public int setRoom(Node node, Room room) {
        this.changes.add(new Changeset(node, node.getRoom()));
        node.setRoom(room);
        return this.lastChange();
    }

    public int lastChange() {
        return this.changes.size() - 1;
    }

    // Undo up to, excluding change i
    public void undo(int step) {
        int cur = this.changes.size() - 1;
        while (cur > step) {
            Changeset d = this.changes.remove(cur);
            d.node.setRoom(d.room);
            cur--;
        }
    };

}


class Changeset {

    Node node;
    Room room;

    public Changeset(Node node, Room room) {
        this.node = node;
        this.room = room;
    }

}
