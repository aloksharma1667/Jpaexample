package com.jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "automails")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name ="mailid")
    private String recipient;
    
	@Column(name ="subject")
    private String subject;
    
	@Column(name ="datetime")
    private LocalDateTime sentDateTime;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public LocalDateTime getSentDateTime() {
		return sentDateTime;
	}
	public void setSentDateTime(LocalDateTime sentDateTime) {
		this.sentDateTime = sentDateTime;
	}
	public User(int id, String recipient, String subject, LocalDateTime sentDateTime) {
		super();
		this.id = id;
		this.recipient = recipient;
		this.subject = subject;
		this.sentDateTime = sentDateTime;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
