package net.guides.springboot.jpa.controller;

import java.io.Serializable;

public class ResourceNotFoundException extends Exception implements Serializable  {

	public ResourceNotFoundException(String exmsg) {
		super(exmsg);
		
	}
	
}
