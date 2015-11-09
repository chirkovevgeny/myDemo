package com.chirkov.specs;

import org.apache.commons.lang.RandomStringUtils;

import com.github.javafaker.Faker;

public class User {
	
	static Faker faker = new Faker();

	public User(String firstName, String lastName, String email, String password ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	public String getName() {
		return firstName;
	}

	public void setName(String name) {
		firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static User createRandom(){
		return new User(faker.name().firstName(), faker.name().lastName(), uniqeEmail(), uniquePasssword(8));
	}
	
	public static String uniqeEmail(){
		String[] splited = faker.internet().emailAddress().split("@");
		return splited[0]+Long.toString(System.currentTimeMillis()).substring(4)+"@"+splited[1];
//		return faker.internet().emailAddress()+Long.toString(System.currentTimeMillis()).substring(4);
	}
	
	public static String uniquePasssword(int length){
		return RandomStringUtils.randomAlphanumeric(length);
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
				+ "]";
	}

}
