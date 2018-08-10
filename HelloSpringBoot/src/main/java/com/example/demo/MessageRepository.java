package com.example.demo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
	public Optional<Message> findFirstByOrderByIdDesc();
}
