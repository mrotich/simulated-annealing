package matchmaker;

import java.util.*;

import matchmaker.Node;

public class ClassNode implements Node {

    private ArrayList<Node> neighbors;
    private String courseNumber;
    private String title;
    private String building;
    private int students, slides;
    private boolean pc, dvd, vcr, oh;
    private int startTime, endTime;
    private BitSet days;
    private Room room;

    //Constructors
    public ClassNode(String cNumber, String cTitle, String meetingTimes,
                     int courseStudents, String building, boolean pc,
                     boolean dvd, boolean oh, boolean vcr, int slides) {
        courseNumber = cNumber;
        title = cTitle;
        String weekDays[] = meetingTimes.split(" ");//Takes the days part and uses a bitmap format
        days = setDays(weekDays[0]);
        String[] dayTime = weekDays[1].split("-");
        startTime = setStartTime(dayTime[0]);//sets time in the format HHMM or HMM(int)
        endTime = setEndTime(dayTime[1]);//sets time in the format HHMM or HMM(int)
        students = courseStudents;
        this.building = building;
        this.pc = pc;
        this.dvd = dvd;
        this.oh = oh;
        this.vcr = vcr;
        this.slides = slides;
        neighbors = new ArrayList<Node>();
	this.room = null;
    }

    //Sets start time as an int the format HHMM or HMM
    public int setStartTime(String time){
        String start[] = time.split(":");
        int hour = Character.getNumericValue(start[0].charAt(0))*10 + Character.getNumericValue(start[0].charAt(1));
        if(time.contains("PM") && hour != 12) hour+=12;
        hour=hour*100;
        int min = Character.getNumericValue(start[1].charAt(0))*10 +Character.getNumericValue(start[1].charAt(1));

        return hour+min;
    }

    //Sets start time as an int the format HHMM or HMM
    public int setEndTime(String time){
        String start[] = time.split(":");
        int hour = Character.getNumericValue(start[0].charAt(0))*10 + Character.getNumericValue(start[0].charAt(1));
        if(time.contains("PM") && hour != 12) hour+=12;
        hour=hour*100;
        int min = Character.getNumericValue(start[1].charAt(0))*10 +Character.getNumericValue(start[1].charAt(1));
        return hour+min;
    }

    //Sets meeting days as a java BitSet
    private BitSet setDays(String days){
        BitSet meetingDays = new BitSet(5);
        if(days.contains("M")){
            meetingDays.set(0);
        }
        if(days.matches(".*T(?!H).*")) {
            meetingDays.set(1);
        }
        if(days.contains("W")){
            meetingDays.set(2);
        }
        if(days.contains("TH")){
            meetingDays.set(3);
        }
        if(days.contains("F")){
            meetingDays.set(4);
        }
        return meetingDays;
    }

    //checks if the meet in the same time
    public boolean sameTime(Node a){
        a = (ClassNode) a;
	return this.days.intersects(a.getDays()) && (this.startTime < a.getEndTime() && a.getStartTime() < this.endTime);
    }

    public  boolean isColored(){
        return room != null;
    }

    public boolean isNeighbor(Node a) {
        return this.neighbors.contains((ClassNode) a);
    }

    public void addNeighbor(Node a){
        neighbors.add((ClassNode) a);
    }

    // Instance variables setters and getters
    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getSize() {
        return students;
    }

    public void setSize(int value) {
        this.students = value;
    }

    public int getSlides() {
        return slides;
    }

    public void setSlides(int slides) {
        this.slides = slides;
    }

    public boolean needPC() {
        return pc;
    }

    public void setPC(boolean pc) {
        this.pc = pc;
    }

    public boolean needDVD() {
        return dvd;
    }

    public void setDVD(boolean dvd) {
        this.dvd = dvd;
    }

    public boolean needOH() {
        return oh;
    }

    public void setOH(boolean oh) {
        this.oh = oh;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setNeighbors(Node[] neighbors) {
        for(int i =0; i<neighbors.length; i++)
            this.neighbors.add((ClassNode) neighbors[i]);
    }

    public Node[] getNeighbors() {
        Node[] n = new Node[neighbors.size()];
        for(int i=0; i<neighbors.size(); i++)
            n[i]=neighbors.get(i);
        return n;
    }

    public void setDays(BitSet a) {
        days = a;
    }

    public BitSet getDays() {
        return days;
    }

    public void setVCR(boolean vcr) {
        this.vcr = vcr;
    }

    public boolean needVCR() {
        return this.vcr;
    }

    public boolean inNeighbors(Room room) {
        Node[] neighbors = this.getNeighbors();
        for (int i=0; i< neighbors.length; i++) {
            if (neighbors[i].getRoom() == room) {
                return true;
            }
        }
        return false;
    }

}
