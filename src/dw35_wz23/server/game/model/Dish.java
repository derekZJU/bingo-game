package dw35_wz23.server.game.model;

import java.io.Serializable;
/**
 * class to indentify the Dish in the game
 * @author dw35, wz23
 *
 */
public class Dish implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6629486822575856493L;
	private String name;
	double latitude;
	double longitude;
	double elevation;
	/**
	 * constructor of the dish
	 * @param n name of the dish
	 * @param latitude latitude of the dish
	 * @param longitude longitude of the dish
	 * @param elevation elevation of the dish
	 */
	public Dish(String n, double latitude, double longitude, double elevation){
		name = n;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
	}
	/**
	 * getter of the latitude
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * setter of the latitude
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * getter of the longitude
	 * @return longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * setter of the longgitude
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * getter of the elevation
	 * @return elevation
	 */
	public double getElevation() {
		return elevation;
	}
	/**
	 * setter of the elevation
	 * @param elevation
	 */
	public void setElevation(double elevation) {
		this.elevation = elevation;
	}
	/**
	 * getter of the name of the dish
	 * @return name of the dish
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter of the name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
