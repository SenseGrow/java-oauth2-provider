package models;

import java.sql.Date;

public class Account {

	private Long id;
	private String name;
	private String url;
	private String contact_person;
	private String phone_number;
	private String email;
	private String address;
	private String city;
	private String state;
	private String country;
	private String zip;
	private String domain;
	private String timezone;
	@Since(1.0)
	private Boolean is_partner;
	@Since(1.0)
	private Boolean is_billing_free;
	@Since(1.0)
	private Date created_at;
	@Since(1.0)
	private Date updated_at;

	public Account(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContactPerson() {
		return contact_person;
	}

	public void setContactPerson(String contact_person) {
		this.contact_person = contact_person;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phone_number;
	}

	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Boolean getIsPartner() {
		return is_partner;
	}

	public void setIsPartner(Boolean isPartner) {
		this.is_partner = isPartner;
	}

	public boolean getIsBillingFree() {
//		if (is_billing_free == null) {
//			return false;
//		}
		return is_billing_free;
	}

	public void setIsBillingFree(Boolean is_billing_free) {
		this.is_billing_free = is_billing_free;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public Date getUpdatedAt() {
		return updated_at;
	}
}
