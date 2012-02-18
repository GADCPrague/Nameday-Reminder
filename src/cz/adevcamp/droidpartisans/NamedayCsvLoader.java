package cz.adevcamp.droidpartisans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;

public class NamedayCsvLoader extends Activity{

	public static class Day {
		public String date;
		public String dateName;
		public Vector<String> contacts = new Vector<String>();

		public Day(String date, String dateName) {
			this.date = date;
			this.dateName = dateName;
		}

		@Override
		public String toString() {
			return "Name: " + dateName + ", date: " + date + ", contacts: " + contacts + "\n" ;
		}
	}

	public NamedayCsvLoader(ListActivity mActivity) {
		
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) throws Exception {
//		
//		Vector<String> names = new Vector<String>();
//
//		names.add("Karina");
//		names.add("Jitka");
//		names.add("Dalimil");
//		names.add("Adam");
//		names.add("Eva");
//		
//		Vector<Day> days = getCalendar(names);
//		System.out.println(days);
//		
//		
//	}

	
	public static Vector<Day> getCalendar(Vector<String> contacts, InputStream is) {
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
		
		for (String contact:contacts) {
			Day day = name2date.get(contact);
			
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
