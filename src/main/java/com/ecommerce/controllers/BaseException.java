package com.ecommerce.controllers;

import lombok.Data;

@Data
public class BaseException {
	
	private int status;
	private String message;
}
