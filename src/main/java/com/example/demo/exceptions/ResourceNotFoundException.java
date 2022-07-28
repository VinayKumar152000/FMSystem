package com.example.demo.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private long fieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		// we need to call base class constructor in order to intalize base class with
		// data we passed
		super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue)); // Post not found with
																									// id : 1
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public long getFieldValue() {
		return fieldValue;
	}
}