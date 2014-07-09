package matchmaker;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import matchmaker.*;

public class Main {

    public static void main(String[] argv) {
        if (argv.length != 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("Usage: java Main <solver>\n\n");
            builder.append("solver:\n");
            builder.append("simple: SimpleSolver\n");
            builder.append("anneal: SimpleSimAnnealing\n");
            System.out.println(builder.toString());
            return;
        }
        String classfile = "FallRequirements.xls";
        String roomfile = "RoomcaplistSept08-4.xls";

        Make maker = (Make) new MakeClass();

        Graph graph = maker.makeGraph(classfile);
        Room[] rooms = maker.makeRooms(roomfile);

        Solver solver = null;
      /*  switch (argv[0]) {
        case "simple":
            solver = (Solver) new SimpleSolver();
            break;
        case "anneal":
            solver = (Solver) new SimpleSimAnnealing();
            break;
        default:
            solver = (Solver) new SimpleSolver();
            break;
        };*/
        solver = (Solver) new SimpleSolver();
        solver.solve(graph, rooms);

        File outputFile = new File("schedule.txt");
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            System.out.println("IOException: Something went wrong");
            return;
        }
        Node[] classes = graph.getNodes();
        StringBuilder builder = new StringBuilder();
        builder.append("Class %s: Room %s\n");
        builder.append("Size %d: %d\n");
        String template = builder.toString();
        for (int i = 0; i < classes.length; i++) {
            Node c = classes[i];
            Room r = c.getRoom();
            try {
                if (r != null) {
                    output.write(String.format(template,
                                               c.getTitle(), r.getName(),
                                               c.getSize(), r.getSize()));
                } else {
                    output.write("Unassigned for " + c.getTitle());
                }
                output.newLine();
            } catch (IOException e) {
                System.out.println("IOException: Something went wrong");
                return;
            }
        }
    }

}
