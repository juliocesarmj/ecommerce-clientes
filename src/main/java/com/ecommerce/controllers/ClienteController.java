package com.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.ClienteRequestDto;
import com.ecommerce.dtos.ClienteResponseDto;
import com.ecommerce.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Clientes")
@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@ApiOperation("Serviço para cadastro de clientes.")
	@PostMapping
	public ResponseEntity<ClienteResponseDto> post(@Valid @RequestBody ClienteRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.save(dto));
	}
}
