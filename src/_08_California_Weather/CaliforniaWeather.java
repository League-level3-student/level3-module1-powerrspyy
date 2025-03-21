package _08_California_Weather;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * OBJECTIVE:
 * 1. Create a program that allows the user to search for the weather
 * conditions of a given city in California. Use the example program below
 * and the Utilities class inside this project to get the temperature data
 * from a day in December 2020.
 * Example: User: Encinitas
 *          Program: Encinitas is Overcast with a tempeature of 59.01 �F
 * 
 * 2. Create a way for the user to specify the weather condition and then
 * list the cities that have those conditions.
 * Example: User: Mostly Cloudy
 *          Program: Long Beach, Pomona, Oceanside, ...
 * 
 * 3. Create a way for the user to enter a minimum and maximum temperature
 * and then list the cities that have temperatures within that range
 * Example: User: minimum temperature �F = 65.0, max temperature �F = 70.0
 *          Program: Fortana, Glendale, Escondido, Del Mar, ...
 * 
 * EXTRA:
 * Feel free to add pictures for specific weather conditions or a thermometer
 * for the temperature. Also If you want your program to get the current day's
 * temperature, you can get a free API key at: https://openweathermap.org/api
 */



public class CaliforniaWeather implements ActionListener{
	JPanel panel;
	JFrame frame;

	JButton search;
	JButton filter;
	JButton byForecast;
	HashMap<String, WeatherData> weatherData;
    void start() {
        weatherData = Utilities.getWeatherData();
        
        // All city keys have the first letter capitalized of each word
//        String cityName = Utilities.capitalizeWords( "National City" );
//        WeatherData datum = weatherData.get(cityName);
//        
//        if( datum == null ) {
//            System.out.println("Unable to find weather data for: " + cityName);
//        } else {
//            System.out.println(cityName + " is " + datum.weatherSummary + " with a temperature of " + datum.temperatureF + " F");
//        }
        
        panel = new JPanel();
        frame = new JFrame("California Weather");

        search = new JButton("Get Forecast");
        
        filter = new JButton("Filter Cities By Range");
        
        byForecast = new JButton("Search With Forecast");
        
        panel.add(search);
        panel.add(byForecast);
        panel.add(filter);
        
        frame.add(panel);
        
        search.addActionListener(this);
        filter.addActionListener(this);
        byForecast.addActionListener(this);
        
        frame.pack();
        frame.setVisible(true);
        
        
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == search) {
			String city = JOptionPane.showInputDialog("Which city would you like to search for?");
			city = Utilities.capitalizeWords(city);
			WeatherData data = weatherData.get(city);
			
			if(data == null) {
				JOptionPane.showMessageDialog(frame, "Unable to find weather data for: " + city);
			}
			else {
				JOptionPane.showMessageDialog(frame, city + " is " + data.weatherSummary + " with a temperature of " + data.temperatureF + "F");

			}
		}
		else if(e.getSource() == filter) {
			String cities = "";
			Integer min = Integer.parseInt(JOptionPane.showInputDialog("What is the minimum temperature you want to search for?"));
			Integer max = Integer.parseInt(JOptionPane.showInputDialog("What is the maximum temperature you want to search for?"));
			
			for(Entry<String, WeatherData> ent: weatherData.entrySet()) {
				Double temp = ent.getValue().temperatureF;
				
				if(temp >= min & temp <= max) {
					cities = cities + ent.getKey() + "\n";
				}
				
			}
			
			JOptionPane.showMessageDialog(frame, cities);
		}
		else if(e.getSource() == byForecast) {
			String forecast = JOptionPane.showInputDialog("What forecast would you like to search for?");
			forecast = Utilities.capitalizeWords(forecast);
			String cities = "";
			for(Entry<String, WeatherData> ent: weatherData.entrySet()) {
				String actualForecast = ent.getValue().weatherSummary;
				
				if(forecast.equals(actualForecast)) {
					cities = cities + ent.getKey() + "\n";
				}
//				System.out.println(forecast + " " + actualForecast);
				
			}
			if(cities == "") {
				cities = "No cities with the forecast " + forecast + " were found!";
			}
			JOptionPane.showMessageDialog(frame, cities);
			
		}
		
	}
}
