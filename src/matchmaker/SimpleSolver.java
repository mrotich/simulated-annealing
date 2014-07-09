package matchmaker;

import java.util.*;

public class SimpleSolver implements Solver {

    public double solve(Graph classes, Room[] rooms) {
        (new SimpleSoluGen()).generate(classes, rooms);
        Cost cost = new SimpleCost();
        return cost.eval(classes);
    }

}
