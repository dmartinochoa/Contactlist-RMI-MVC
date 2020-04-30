package modelo;

import java.io.Serializable;

public class ContactInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String phoneNumber;
	private String cellNumber;
	private String email;
	private String website;
	private String notes;

	public ContactInfo(String name, String address, String phoneNumber, String cellNumber, String email, String website,
			String notes) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.cellNumber = cellNumber;
		this.email = email;
		this.website = website;
		this.notes = notes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
