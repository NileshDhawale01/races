package com.nsd.race.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResorcesNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String resource;
	
	private String fieldName;
	
	private Integer value;
	
	public ResorcesNotFoundException(String resource , String fieldName , Integer value) {
		
		super(String.format("%s not found with %s : %s",resource , fieldName,value));
		this.resource = resource;
		this.fieldName = fieldName;
		this.value = value;
	}
	
public ResorcesNotFoundException(String resource , String fieldName) {
		
		super(String.format("%s not found with %s ",resource , fieldName));
		this.resource = resource;
		this.fieldName = fieldName;
	}
}
