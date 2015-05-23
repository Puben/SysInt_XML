/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee;

/**
 *
 * @author dcj976
 */
public class Employee {
     private String ID;
     private String Firstname;
     private String Lastname;
     private int age;
     private double salary;

     public Employee(String ID, String Firstname, String Lastname, int age, double salary) {
          this.ID = ID;
          this.Firstname = Firstname;
          this.Lastname = Lastname;
          this.age = age;
          this.salary = salary;
     }

     @Override
     public String toString() {
          return "<" + ID + ", " + Firstname + ", " + Lastname + ", " + age + ", "
                                   + salary + ">";
     }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
     
     
}