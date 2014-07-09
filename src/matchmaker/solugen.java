package matchmaker;

import java.util.*;

/* Solution Generator
 */
interface SoluGen {
    public void generate(Graph classes, Room[] rooms);
}

class SimpleSoluGen implements SoluGen {

    public void generate(Graph classes, Room[] rooms) {
        Node[] classesList = classes.getNodes();
        this.sortClasses(classesList);
        this.sortRooms(rooms);
        for (int i_class=0; i_class < classesList.length; i_class++) {
            int i_room = 0;
            while ((rooms[i_room].getSize() < classesList[i_class].getSize()) ||
                   (classesList[i_class].inNeighbors(rooms[i_room])) ||
                   (classesList[i_class].needPC() && !rooms[i_room].hasPC()) ||
                   (classesList[i_class].needDVD() && !rooms[i_room].hasDVD()) ||
                   (classesList[i_class].needVCR() && !rooms[i_room].hasVCR()) ||
                   (classesList[i_class].needOH() && !rooms[i_room].hasOH()) ||
                   (classesList[i_class].getSlides() <=
                    rooms[i_room].getSlides())
                   ) {
                i_room++;
                if (i_room >= rooms.length) {
                    i_room = -1;
                    break;
                }
            }
            if (i_room >= 0) {
                classesList[i_class].setRoom(rooms[i_room]);
            }
        }
    }

    /* Sort ascending */
    private void sortClasses(Node[] classes) {
        Arrays.sort(classes, new NodeComparer());
    }

    /* Sort ascending */
    private void sortRooms(Room[] rooms) {
        Arrays.sort(rooms, new RoomComparer());
    }

}
