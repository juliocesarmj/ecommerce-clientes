package com.ecommerce.dtos;

import com.ecommerce.models.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponseDto {
	
	private String message;
	private String accessToken;
	private Cliente data;
	
}
