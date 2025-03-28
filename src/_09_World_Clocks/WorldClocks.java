package _09_World_Clocks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/*
 * You task is to create a java program that:
 * 1. Displays the time for multiple cities around the world on one display.
 * 2. Gives the user the ability to add a city to the display. One possible
 *    way to do this is to create a HashMap of city names and their
 *    corresponding time zones, e.g. HashMap<String, TimeZone>, then use each
 *    city's TimeZone to get the current date/time every second using a
 *    Timer object (see example code below).
 * 
 * The code below is an example of how to print out a clock for San Diego.
 * Use the ClockUtilities class to find the time zone of each city, then use
 * Calendar.getInstance to return a Calendar object to get the current time for
 * that city. Example:
 *   TimeZone timeZone = clockUtil.getTimeZoneFromCityName("San Diego, US");
 *   Calendar c = Calendar.getInstance(timeZone);
 *   System.out.println("Full date and time: " + calendar.getTime());
 * 
 * NOTE: The program may take a second or two to execute
 * 
 * Calendar class:
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 */

public class WorldClocks implements ActionListener {
    ClockUtilities clockUtil;
    Timer timer;
    ArrayList<TimeZone> timeZones;

    JFrame frame;
    JPanel panel;
    JButton addCity;
    ArrayList<JTextArea> textAreas;
    
    int cities = 0;
    
    ArrayList<String> city_names;
    String dateStr;
    String timeStr;
    
    public WorldClocks() {
        clockUtil = new ClockUtilities();
        
        timeZones = new ArrayList<TimeZone>();
        
        city_names = new ArrayList<String>();
        
        // The format for the city must be: city, country (all caps)
        
//        Calendar calendar = Calendar.getInstance(timeZones.get(0));
//        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
//        String dayOfWeek = calendar.getDisplayName( Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
//        dateStr = dayOfWeek + " " + month + " " + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.YEAR);
//        
//        System.out.println(dateStr);

        // Sample starter program
        frame = new JFrame("WorldClocks");
        panel = new JPanel();
        textAreas = new ArrayList<JTextArea>();
        addCity = new JButton("Add City");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(100, 100);
        frame.add(panel);
        panel.add(addCity);
        addCity.addActionListener(this);
        
        // This Timer object is set to call the actionPerformed() method every
        // 1000 milliseconds
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == timer) {
	    	for(int i = 0; i < timeZones.size(); i++) {
	    		
		        Calendar c = Calendar.getInstance(timeZones.get(i));
		        String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
		        String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + "]";
		        timeStr = militaryTime + twelveHourTime;
		        String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		        String dayOfWeek = c.getDisplayName( Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
		        dateStr = dayOfWeek + " " + month + " " + c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.YEAR);
		        
		//        System.out.println(timeStr);
		        textAreas.get(i).setText(city_names.get(i) + "\n" + dateStr + "\n" + timeStr);
	    	}
	        frame.pack();
    	}
    	else if(e.getSource() == addCity) {
    		String city = JOptionPane.showInputDialog("Enter a city in this format: Chicago, US");
    		city_names.add(city);
    		timeZones.add(clockUtil.getTimeZoneFromCityName(city));
    		textAreas.add(new JTextArea());
    		
    		cities ++;
    		
    		panel.add(textAreas.get(cities-1));
    		
    	}
    }
}
