package matchmaker;

import java.util.BitSet;
import java.util.Arrays;

public class Times {

    private static final String[] _Days =
    {
        "Mon", "Tue", "Wed", "Thu", "Fri"
    };

    private static final String[] _Times =
    {
        "8:00", "8:30",
        "9:00", "9:30",
        "10:00", "13:00",
        "11:00", "11:30",
        "12:00", "12:30",
        "13:00", "13:30",
        "14:00", "14:30",
        "15:00", "15:30",
        "16:00", "16:30",
        "17:00", "17:30",
        "18:00", "18:30",
        "19:00", "19:30",
    };

    public static String[] getTimes() {
        return _Times.clone();
    }

    public static String[] getDays() {
        return _Days.clone();
    }

    public static BitSet makeTimesBits() {
        return (new BitSet(_Days.length * _Times.length));
    }

    /* Returns slot index or -1 if failed (bad key)
     */
    public static int time2slot(String day, String time) {
        int i_day = Arrays.asList(_Days).indexOf(day);
        if (i_day == -1) return -1;
        int i_time = Arrays.asList(_Times).indexOf(time);
        if (i_time == -1) return -1;
        return (i_day * _Times.length) + i_time;
    }

    public static String[] slot2time(int i) {
        String[] time = new String[2];
        int i_day = i / _Times.length;
        int i_time = i - i_day;
        time[0] = _Days[i_day];
        time[1] = _Days[i_time];
        return time;
    }

}
