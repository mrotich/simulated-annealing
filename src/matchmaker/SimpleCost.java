package matchmaker;

public class SimpleCost implements Cost {

    private static final double NULL_MULT = 1.5;
    private static final double COLLIDE_PENALTY = 9999;
    private static final double EQUIP_PENALTY = 10;

    public double eval(Graph graph) {
        double cost = 0;
        Node[] nodes = graph.getNodes();
        int roomsDone = 0;
        int roomsSkipped = 0;
        // For each node
        for (int i = 0; i < nodes.length; i++) {
            Node n = nodes[i];
            Room r = n.getRoom();
            // skip if uncolored, add cost later
            if (r == null) {
                roomsSkipped++;
                continue;
            } else {
                roomsDone++;
            }
            // add penalty and continue if same as neighbor
            if (n.inNeighbors(r)) {
                cost += COLLIDE_PENALTY;
                continue;
            }
            // add various per class costs
            cost += Math.pow((double)(r.getSize()-n.getSize()), 2) / 5;
            if (n.needPC() && !r.hasPC()) {
                cost += EQUIP_PENALTY;
            }
            if (n.needDVD() && !r.hasDVD()) {
                cost += EQUIP_PENALTY;
            }
            if (n.needVCR() && !r.hasVCR()) {
                cost += EQUIP_PENALTY;
            }
            if (n.needOH() && !r.hasOH()) {
                cost += EQUIP_PENALTY;
            }
            if (n.getSlides() < r.getSlides()) {
                cost += EQUIP_PENALTY;
            }
        }
        // Add cost for skipped rooms
        cost += cost / roomsDone * roomsSkipped;
        return cost;
    }

    // handle unassigned penalty estimate?
    public double deltaEval(Graph graph, Node node, Room room) {
        double cost = 0;
        Node n = node;
        Room r = room;
        // skip if uncolored
        if (r == null) {
            cost += r.getSize() * NULL_MULT;
        } else if (n.inNeighbors(r)) {
            // add penalty and continue if same as neighbor
            cost += COLLIDE_PENALTY;
        } else {
            // add various per class costs
            cost += Math.pow((double)(r.getSize()-n.getSize()), 2) / 5;
            if (n.needPC() && !r.hasPC()) {
                cost += EQUIP_PENALTY;
            }
            if (n.needDVD() && !r.hasDVD()) {
                cost += EQUIP_PENALTY;
            }
            if (n.needVCR() && !r.hasVCR()) {
                cost += EQUIP_PENALTY;
            }
            if (n.needOH() && !r.hasOH()) {
                cost += EQUIP_PENALTY;
            }
            if (n.getSlides() < r.getSlides()) {
                cost += EQUIP_PENALTY;
            }
        }

        // minus previous costs (gain)
        r = node.getRoom();
        // skip if uncolored
        if (r == null) {
            cost -= r.getSize() * NULL_MULT;
        } else if (n.inNeighbors(r)) {
            // add penalty and continue if same as neighbor
            cost -= COLLIDE_PENALTY;
        } else {
            // add various per class costs
            cost -= Math.pow((double)(r.getSize()-n.getSize()), 2) / 5;
            if (n.needPC() && !r.hasPC()) {
                cost -= EQUIP_PENALTY;
            }
            if (n.needDVD() && !r.hasDVD()) {
                cost -= EQUIP_PENALTY;
            }
            if (n.needVCR() && !r.hasVCR()) {
                cost -= EQUIP_PENALTY;
            }
            if (n.needOH() && !r.hasOH()) {
                cost -= EQUIP_PENALTY;
            }
            if (n.getSlides() < r.getSlides()) {
                cost -= EQUIP_PENALTY;
            }
        }

        return cost;
    }

}
