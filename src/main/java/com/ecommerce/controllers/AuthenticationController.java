package com.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.AuthenticationRequestDto;
import com.ecommerce.dtos.AuthenticationResponseDto;
import com.ecommerce.services.ClienteService;

import io.swagger.annotations.Api;

@Api(tags = "Autenticação")
@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<AuthenticationResponseDto> auth(@RequestBody @Valid AuthenticationRequestDto dto) {
		return ResponseEntity.ok(this.clienteService.auth(dto));
	}
}
