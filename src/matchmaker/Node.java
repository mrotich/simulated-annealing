package matchmaker;

import java.util.*;

public interface Node {

    public boolean sameTime(Node n); //returns true if classes held at the same time
    public boolean isNeighbor(Node n); //true if classes are neighbors //held at the same time
    public void addNeighbor(Node n); //add a neighbor/ class held at sameTime
    public Room getRoom();
    public void setRoom(Room value);

    public String getCourseNumber();
    public String getTitle();
    public String getBuilding();
    public int getSlides();
    public boolean needPC();
    public boolean needDVD();
    public boolean needOH();
    public boolean needVCR();
    public int getStartTime();
    public int getEndTime();
    public int getSize();
    public Node[] getNeighbors();
    public BitSet getDays();
    public boolean inNeighbors(Room room);  // if room is used by neighbors

}
