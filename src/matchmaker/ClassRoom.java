package matchmaker;

import java.util.*;

class ClassRoom implements Room {

    public ClassRoom(String name, String type, int size, boolean pc,
                     boolean dvd, boolean vcr, boolean oh, int slides) {
        this._Name = name;
        this._Type = type;
        this._Size = size;
        this._PC = pc;
        this._DVD = dvd;
        this._VCR = vcr;
        this._OH = oh;
        this._Slides = slides;
    }

    private static final String[] _ValidTypes =
    {
        "lecture", "seminar",
    };

    public String[] getValidTypes() {
        return this._ValidTypes.clone();
    }

    private String _Name;
    public String getName() {
        return this._Name;
    }
    public void setName(String value) {
        this._Name = value;
    }

    private int _Size;
    public int getSize() {
        return this._Size;
    }
    public void setSize(int value) {
        this._Size = value;
    }

    private String _Type;
    public String getType() {
        return this._Type;
    }
    public void setType(String value) {
        this._Type = value;
    }

    private boolean _PC;
    public boolean hasPC() {
        return this._PC;
    }
    public void setPC(boolean value) {
        this._PC = value;
    }

    private boolean _DVD;
    public boolean hasDVD() {
        return this._DVD;
    }
    public void setDVD(boolean value) {
        this._DVD = value;
    }

    private boolean _VCR;
    public boolean hasVCR() {
        return this._VCR;
    }
    public void setVCR(boolean value) {
        this._VCR = value;
    }

    private boolean _OH;
    public boolean hasOH() {
        return this._OH;
    }
    public void setOH(boolean value) {
        this._OH = value;
    }

    private int _Slides;
    public int getSlides() {
        return this._Slides;
    }
    public void setSlides(int value) {
        this._Slides = value;
    }

}

class RoomComparer implements Comparator<Room> {

    public int compare(Room o1, Room o2) {
        int x = o1.getSize();
        int y = o2.getSize();
        if (x > y) return 1;
        else if (x == y) return 0;
        else return -1;
    }

}
