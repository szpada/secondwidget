package com.widget.zapis;

import java.io.Serializable;

import android.util.Log;


/**
 * 
 * @author Twoja Stara
 *
 * Klasa zawiera settingsy
 */
public class UserSettings implements Serializable {
	
	int salary;
	int period;  // per year/per month/per hour
	
	
	public UserSettings(){
		salary = 1;
		period = 1;
		
	}


	/**
	 * @return the salary
	 */
	public int getSalary() {
		return salary;
	}


	/**
	 * @param salary the salary to set
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}


	/**
	 * @return the period
	 */
	public int getPeriod() {
		return period;
	}


	/**
	 * @param period the period to set
	 */
	public void setPeriod(int period) {
		this.period = period;
	}
	
	
}
