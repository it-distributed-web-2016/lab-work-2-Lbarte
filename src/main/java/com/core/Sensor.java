package core;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

/**
* Generates data from sensor
*
* @author FoxArt
* @version 2.1
* @since 11.28.2016
*/
public class Sensor {

	private Random randomInstance = new Random();
	
	/**
	*Generates temperature in the range from -20~+5/+5~+40
	*
	*@param cold Define cold weather and lower temperature
	*/
	protected int getTemperature (boolean cold) {
		return cold?(randomInstance.nextInt(25) - 20):(randomInstance.nextInt(35) - 5);
	}
	
	/**
	*Generates delay in the range 1~10
	*/
	protected int getDelay () {
		return (randomInstance.nextInt(9) + 1);
	}
	
	/**
	*Generates humidity in the range 20~100
	*/
	protected int getHumidity () {
		return (randomInstance.nextInt(80) + 20);
	}
	
	private DecimalFormat decForm;
	
	/**
	*Defines decimal format to generate latitude and longitude
	*/
	private void defineDecFormat() {
		//locale need to separate decimals by dots
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
		decForm = new DecimalFormat("#.######", otherSymbols);
		decForm.setRoundingMode(RoundingMode.HALF_UP);
	}
	
	/**
	*Generates latitude with defined decimal format and the range -85~85
	*/
	protected String getLatitude () {
		defineDecFormat();
		return decForm.format((randomInstance.nextDouble() * 170) - 85);
	}
	
	/**
	*Generates longitude with defined decimal format and the range -170~170
	*/
	protected String getLongitude () {
		defineDecFormat();
		return decForm.format((randomInstance.nextDouble() * 340) - 170);
	}
}
