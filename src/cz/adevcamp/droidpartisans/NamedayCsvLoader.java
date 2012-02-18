package cz.adevcamp.droidpartisans;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;


public class NamedayCsvLoader {

	public static class Day {
		public String date;
		public String dateName;
		public Vector<Contact> contacts = new Vector<Contact>();

		public Day(String date, String dateName) {
			this.date = date;
			this.dateName = dateName;
		}

		@Override
		public String toString() {
			return "\nName: " + dateName + ", date: " + date + ", contacts: " + contacts;
		}
	}
	
	/**
	 * @param args
	 */
//	public static void main(String[] args) throws Exception {
//		
//		Vector<Contact> contacts = new Vector<Contact>();
//
//		contacts.add(new Contact(1, "Karina", "Janečková", "555263882"));
//		contacts.add(new Contact(1, "Jitka", "Telecí", "555263882"));
//		contacts.add(new Contact(1, "Dalimil", "Míšová", "555263882"));
//		contacts.add(new Contact(1, "Adam", "Jandová", "555263882"));
//		contacts.add(new Contact(1, "Eva", "Novotná", "555263882"));
//		
//		Vector<Day> days = getCalendar(contacts	);
//		System.out.println(days);
//		
//		
//	}

	public static Vector<Day> getCalendar(Vector<Contact> contacts, InputStream is) {

		Vector<Day> days = new Vector<Day>();
		Map<String, Day> name2date = new TreeMap<String, Day>();

		
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line = br.readLine();

			while (line != null) {
				String[] parsedRow = line.split(",");
				String name = parsedRow[0];
				String date = parsedRow[1];

				Day day = new Day(date, name);
				days.add(day);
				
				String[] names = name.split(" ");
				
				for (String tinyName : names) {
					name2date.put(tinyName, day);
				}

				line = br.readLine();
			}

//			System.out.println(days.toString());
//			System.out.println(name2date);
			
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
		
		for (Contact contact:contacts) {
			Day day = name2date.get(contact.getName());
			
			if (null != day) {
				day.contacts.add(contact);
			} else {
				System.err.println(contact);
			}
		}

//		String name = "Kateřina";
//		System.out.println(name + " ma svatek v " + name2date.get(name));
		return days;
	}

}
