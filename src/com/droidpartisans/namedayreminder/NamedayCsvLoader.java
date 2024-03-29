package com.droidpartisans.namedayreminder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Provides Name Day calendar populated with contacts 
 */

public class NamedayCsvLoader {

	/**
	 * Entry in the calendar, it contains date, name (or name of the holiday),
	 * and list of contacts who's given name matches
	 */
	public static class Day {
		public String date;			// e.g. "21.2."
		public String dateName;		// e.g. "Lenka"
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
	 * Poor man's unit test
	 */
	public static void main(String[] args) throws Exception {
		
		/* check Czech calendar */
		Vector<Contact> contacts = new Vector<Contact>();

		contacts.add(new Contact(1, "Karina", "Janečková", "555263882"));
		contacts.add(new Contact(1, "Jitka", "Telecí", "555263882"));
		contacts.add(new Contact(1, "Dalimil", "Míšová", "555263882"));
		contacts.add(new Contact(1, "Adam", "Janda", "555263882"));
		contacts.add(new Contact(1, "Eva", "Novotná", "555263882"));
		contacts.add(new Contact(1, "Jan", "Nepomuk", "555263882"));
		contacts.add(new Contact(1, "Honza", "Veliký", "555363882"));
		contacts.add(new Contact(1, "Eliška", "Pravá", "555363882"));
		contacts.add(new Contact(1, "Eliska", "Leva", "555363882"));
		contacts.add(new Contact(1, "Věra", "Pravá", "555363882"));
		contacts.add(new Contact(1, "Vera", "Leva", "555363882"));
		contacts.add(new Contact(1, "vera", "treti", "555363882"));
		
		Vector<Day> days = getCalendar(contacts, new FileInputStream("res/raw/namedays_cz_rev.csv"));
		System.out.println(days);
		
		/* check Slovak calendar */
		contacts = new Vector<Contact>();

		contacts.add(new Contact(1, "Katarína", "Janečková", "555263882"));
		contacts.add(new Contact(1, "Katka", "Telecí", "555263882"));
		contacts.add(new Contact(1, "Adam", "Janda", "555263882"));
		contacts.add(new Contact(1, "Eva", "Novotná", "555263882"));
		contacts.add(new Contact(1, "Jan", "Nepomuk", "555263882"));
		contacts.add(new Contact(1, "Janko", "Skvelý", "555263882"));
		contacts.add(new Contact(1, "Štefan", "Pravý", "555363882"));
		contacts.add(new Contact(1, "stevo", "velky", "555363882"));
		
		days = getCalendar(contacts, new FileInputStream("res/raw/namedays_sk_rev.csv"));
		System.out.println(days);
	}

	/**
	 * Create Name Day calendar, populate calendar with contacts
	 * 
	 * @param contacts - known contacts - given name from contact data is used for processing
	 * @param is - input stream of CSV formated Name Day calendar
	 * @return
	 */
	public static Vector<Day> getCalendar(Vector<Contact> contacts, InputStream is) {
		Vector<Day> calendar = new Vector<Day>();		// chronological name day calendar of the whole year
		Map<String, Day> name2date; 					// index mapping names to entries in the calendar
		Collator collator = Collator.getInstance();		// initialised with default value - just for the case

		/* our custom collation - system collation for czech does not work well (s != š)*/
		try {
			String rule = "< a,á,ä,A,Á < b,B < c,č,C,Č " +
					"< d,ď,D,Ď < e,é,ě,E,É,Ě < f,F < g,G < h,H " +
					"< i,í,I,Í < j,J < k,K < l,ĺ,L,Ĺ < m,M " +
					"< n,ň,N,Ň < o,ó,O,Ó < p,P < r,ř,ŕ,R,Ř,ŕ " +
					"< s,š,S,Š < t,ť,T,Ť < u,ú,ů,U,Ú,Ů < v,V " +
					"< x,X < y,ý,Y,Ý < z,ž,Z,Ž";
			
			collator = new RuleBasedCollator(rule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* ignore case and accents */
		collator.setStrength(Collator.PRIMARY);
		collator.setDecomposition(Collator.CANONICAL_DECOMPOSITION);
		
//		System.out.println("Locale: e " + collator.compare("a", "A"));
//		System.out.println("Locale: f " + collator.compare("a", "á"));
//		System.out.println("Locale: g " + collator.compare("š", "s"));
		
		/* create index which will ignore case and accents */
		name2date = new TreeMap<String, Day>(collator);
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = br.readLine();

			while (line != null) {
				String[] parsedRow = line.split(",");	// row has format "Ivana,Ivona,...,28.12.,Iva,nickname2,..."
				String date = null;
				Vector<String> names = new Vector<String>();	//< all names assigned to date
				String calendarName = null;						//< 'official' name shown in the calendar 
				
				/* extract names and date */
				for (String field : parsedRow) {
					field = field.trim();
					
					/* record date */
					if (field.matches("\\d+\\.\\d+\\.")) {
						date = field;
					} /* record name */
					else {
						String[] namesTmp = field.split(" a ");	// name can have format "Adam a Eva"
						
						/* record all names */
						for (String name : namesTmp) {
							names.add(name);
						}
						
						/* 'official' calendar name is before the date */
						if (null == date) {
							if (null == calendarName) {
								calendarName = field;
							} /* more official names are separated by comma */
							else {
								calendarName += ", " + field;
							}
						}
					}
				}

				/* create day */
				Day day = new Day(date, calendarName);	
				
				/* append entry to calendar */
				calendar.add(day);
				
				/* populate index mapping names to entries in calendar */
				for (String name : names) {
					name2date.put(name, day);
				}

				/* read next line from CSV database */
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
		
		/* populate calendar with our contacts */
		for (Contact contact:contacts) {
			
			/* find calendar entry according to given name of our contact */
			Day day = name2date.get(contact.getName());
			
			if (null != day) {
				day.contacts.add(contact);	// our contact will celebrate on the given day
			} else {
				System.err.println(contact); // we cannot determine entry in the calendar
			}
		}

//		String name = "Kateřina";
//		System.out.println(name + " ma svatek v " + name2date.get(name));
		return calendar;
	}

}
