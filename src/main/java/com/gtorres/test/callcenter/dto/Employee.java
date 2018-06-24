package com.gtorres.test.callcenter.dto;

public class Employee extends Dto {

	private TypeEmployee typeEmployee;
	private String name;
	private boolean isBusy;

	/**
	 * @return the typeEmployee
	 */
	public TypeEmployee getTypeEmployee() {
		return typeEmployee;
	}

	/**
	 * @param typeEmployee
	 *            the typeEmployee to set
	 */
	public void setTypeEmployee(TypeEmployee typeEmployee) {
		this.typeEmployee = typeEmployee;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the isBusy
	 */
	public boolean isBusy() {
		return isBusy;
	}

	/**
	 * @param isBusy the isBusy to set
	 */
	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

}
