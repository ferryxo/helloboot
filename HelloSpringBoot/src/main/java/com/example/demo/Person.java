package com.example.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

	@Id
	@Column(name="email")
	public String email;
	@Column
	public String fullname;
	@Column
	public String address;
	@Column
	public Date birthday;
	
	public Person() {}
	public Person(String email, String fullname, String address, Date birthday) {
		this.email = email;
		this.fullname = fullname;
		this.address = address;
		this.birthday = birthday;
	}
}
