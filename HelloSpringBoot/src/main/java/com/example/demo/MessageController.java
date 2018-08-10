package com.example.demo;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloapp")
public class MessageController {
	
	@Autowired
	MessageService msgSvc;
	
	@PostMapping("/hello")
	public ResponseEntity enqHello(@RequestBody Message m) {
		msgSvc.enqueueMessage(m);
		return new ResponseEntity(HttpStatus.OK);	
	}
	
	@GetMapping("/hello")
	public Message[] getHello() throws ParseException {
		return msgSvc.getMessagesInQueue();
	}

	@DeleteMapping("/hello")
	public Message deqHello() {
		return msgSvc.dequeueMessage();
	}
}
