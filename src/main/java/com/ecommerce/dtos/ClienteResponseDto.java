package com.ecommerce.dtos;

import com.ecommerce.models.Cliente;

import lombok.Data;

@Data
public class ClienteResponseDto {

	private String message;
	private Cliente data;
	
	public static ClienteResponseDto response(Cliente cliente, String message) {
		ClienteResponseDto response = new ClienteResponseDto();
		response.setData(cliente);
		response.setMessage(message);
		
		return response;
	}
}
