//Name:sama wahidee
//Id:1211503
//Section:1
package application;

import java.util.Date;

public class Martyr {
	private String name;
	private int age = 0;
	private Date dateOfDeath;
	private char gender = 'N';

	public Martyr() {

	}

	public Martyr(String name) {
		this.name = name;
	}

	public Martyr(String name, int age, Date dateOfDeath, char gender) {
		this.name = name;
		this.age = age;
		this.dateOfDeath = dateOfDeath;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String toString() {
		return "Martyr name: " + name + "\nMartyr age: " + age + "\nMartyr dateOfDeath: " + dateOfDeath
				+ "\nMartyr gender: " + gender + "\n";
	}
}
