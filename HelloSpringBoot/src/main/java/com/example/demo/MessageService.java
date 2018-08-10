package com.example.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	
	@Autowired
	MessageRepository msgRepo;
	@Autowired
	PersonRepository personRepo;
		
	public void enqueueMessage(Message m) {
		Optional<Person> sender = personRepo.findById(m.sender.email);
		if(sender.isPresent())m.sender = sender.get();
		Optional<Person> reciever = personRepo.findById(m.reciever.email);
		if(reciever.isPresent())m.reciever = reciever.get();
		
		m=msgRepo.save(m);
	}
	
	public Message[] getMessagesInQueue() {
		ArrayList<Message> msgs = new ArrayList<Message>();
		msgRepo.findAll().forEach(msgs::add);
		return  msgs.toArray(new Message[msgs.size()]);
	}
	
	public Message dequeueMessage() {
		Optional<Message> m = msgRepo.findFirstByOrderByIdDesc();
		if(m.isPresent()) {
			msgRepo.delete(m.get());
			return m.get();
		}else
			return null;
	}
}
