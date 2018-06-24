/**
 * 
 */
package com.gtorres.test.callcenter.dto;

import java.time.LocalTime;

/**
 * @author gtorresoft
 *
 */
public class Call extends Dto {

	private int callTimeInSeconds;
	private LocalTime timeReceived;
	private LocalTime timeAsigned;
	private LocalTime timeFinished;
	private Employee employeeAsigned;
	
	public Call(Long id) {
		super();
		this.setId(id);
	}
	/**
	 * 
	 */
	public Call() {
		super();
	}

	/**
	 * @return the callTimeInSeconds
	 */
	public int getCallTimeInSeconds() {
		return callTimeInSeconds;
	}

	/**
	 * @param callTimeInSeconds
	 *            the callTimeInSeconds to set
	 */
	public void setCallTimeInSeconds(int callTimeInSeconds) {
		this.callTimeInSeconds = callTimeInSeconds;
	}
	/**
	 * @return the timeReceived
	 */
	public LocalTime getTimeReceived() {
		return timeReceived;
	}
	/**
	 * @param timeReceived the timeReceived to set
	 */
	public void setTimeReceived(LocalTime timeReceived) {
		this.timeReceived = timeReceived;
	}
	/**
	 * @return the timeAsigned
	 */
	public LocalTime getTimeAsigned() {
		return timeAsigned;
	}
	/**
	 * @param timeAsigned the timeAsigned to set
	 */
	public void setTimeAsigned(LocalTime timeAsigned) {
		this.timeAsigned = timeAsigned;
	}
	/**
	 * @return the timeFinished
	 */
	public LocalTime getTimeFinished() {
		return timeFinished;
	}
	/**
	 * @param timeFinished the timeFinished to set
	 */
	public void setTimeFinished(LocalTime timeFinished) {
		this.timeFinished = timeFinished;
	}
	/**
	 * @return the employeeAsigned
	 */
	public Employee getEmployeeAsigned() {
		return employeeAsigned;
	}
	/**
	 * @param employeeAsigned the employeeAsigned to set
	 */
	public void setEmployeeAsigned(Employee employeeAsigned) {
		this.employeeAsigned = employeeAsigned;
	}
}
