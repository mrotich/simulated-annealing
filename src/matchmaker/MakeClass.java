package matchmaker;

import java.util.ArrayList;
import java.io.*;
import jxl.*;

public class MakeClass implements Make {

	public Graph makeGraph(String file) {
		Graph graph = (Graph) new ClassGraph();
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(new File(file));
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
		Sheet sheet = workbook.getSheet(0);
		for (int i = 2; i < sheet.getRows(); i++) {
			int slides = 0;
			try {
				slides = Integer.parseInt(sheet.getCell("H"+i).getContents());
			} catch (NumberFormatException e) {
			}
			String times = sheet.getCell("D"+i).getContents();
			// Temporary workaround for empty times
			// Temporary workaround for TBD times
			// Temporary workaround for TBA times
			// The registrar can suck it
			if (!times.isEmpty() && !times.matches(".*(TBD|TBA).*")) {
				Node course = (Node) new ClassNode(
						sheet.getCell("A"+i).getContents(),
						sheet.getCell("B"+i).getContents(),
						times,
						Integer.parseInt(sheet.getCell("C"+i).getContents()),
						null, // building
						sheet.getCell("E"+i).getContents().equals("X"), // pc
						sheet.getCell("F"+i).getContents().equals("X"), // dvd
						sheet.getCell("I"+i).getContents().equals("X"), // oh
						sheet.getCell("G"+i).getContents().equals("X"), // vcr
						slides
						);
				for (Node otherCourse : graph.getNodes()) {
					if (course.sameTime(otherCourse)) {
						course.addNeighbor(otherCourse);
						otherCourse.addNeighbor(course);
					}
				}
				graph.addNode(course);
			}
		}
		workbook.close();
		return graph;
	}

	public Room[] makeRooms(String file) {
		ArrayList<Room> roomList = new ArrayList<Room>();
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(new File(file));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		Sheet sheet = workbook.getSheet(0);
		String building = "";
		for (int i = 2; i < sheet.getRows(); i++) {
			if (!sheet.getCell("A"+i).getContents().equals("")) {
				building = sheet.getCell("A"+i).getContents();
			}
			if (!sheet.getCell("B"+i).getContents().equals("") && !building.equals("Building")) {
				String name = building + " " + sheet.getCell("B"+i).getContents();
				
				String type;
				if (sheet.getCell("C"+i).getContents().equals(""))
					type = "lecture";
				else
					type = "seminar";
				
				int size = 0;
				try {
					size = Integer.parseInt(sheet.getCell("F"+i).getContents());
				} catch (NumberFormatException e) {
					//if no size specified, guess size based on type
					//guesses have no basis in reality
					if (type.equals("lecture"))
						size = 50;
					else
						size = 25;
				}
				
				int slides;
				try {
					slides = Integer.parseInt(sheet.getCell("H"+i).getContents());
				} catch (NumberFormatException e) {
					slides = 0;
				}
				boolean pc = (!sheet.getCell("O"+i).getContents().equals(""));
				boolean dvd = (!sheet.getCell("M"+i).getContents().equals(""));
				boolean vcr = (!sheet.getCell("N"+i).getContents().equals(""));
				boolean oh = (!(sheet.getCell("I"+i).getContents().equals("") &&
						sheet.getCell("P"+i).getContents().equals("")));

				Room room = new ClassRoom(name, type, size, pc, dvd,
						vcr, oh, slides);
				roomList.add(room);
			}
		}

		Room[] roomArray = new Room[roomList.size()];
		for (int i = 0; i < roomArray.length; i++) {
			roomArray[i] = roomList.get(i);
			System.out.println(roomArray[i].getName());
		}

		workbook.close();
		return roomArray;
	}
}
