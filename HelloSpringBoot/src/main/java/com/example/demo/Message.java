package com.example.demo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {
	
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	@Column
	public String message;
	@ManyToOne(targetEntity=Person.class, cascade=CascadeType.PERSIST,  fetch=FetchType.EAGER)
	@JoinColumn(name="senderId",referencedColumnName="email", insertable=true, updatable=true)
	public Person sender;
	@ManyToOne(targetEntity=Person.class, cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name="recieverId",referencedColumnName="email", insertable=true, updatable=true)
	public Person reciever;
	@Column
	public LocalDateTime timestamp;
	
	public Message() {}
	public Message(String msg, Person s, Person r, LocalDateTime time) {
		this.message = msg;
		this.sender = s;
		this.reciever = r;
		this.timestamp = time;
	}
}