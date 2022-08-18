package com.ecommerce.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.components.ModelmapperInstance;
import com.ecommerce.dtos.AuthenticationRequestDto;
import com.ecommerce.dtos.AuthenticationResponseDto;
import com.ecommerce.dtos.ClienteRequestDto;
import com.ecommerce.dtos.ClienteResponseDto;
import com.ecommerce.models.Cliente;
import com.ecommerce.repositories.IClienteRepository;
import com.ecommerce.security.TokenAuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final IClienteRepository clienteRepository;

	private final ModelmapperInstance mapper;

	private final TokenAuthenticationService tokenService;

	public ClienteResponseDto save(ClienteRequestDto dto) {

		this.validaEmail(dto.getEmail());
		this.validaTelefone(dto.getTelefone());

		Cliente cliente = this.mapper.mapper().map(dto, Cliente.class);
		cliente.setSenha(getHashMd5(dto.getSenha()));
		cliente.atualizaDatas();

		this.clienteRepository.save(cliente);

		return ClienteResponseDto.response(cliente, "Cadastro realizado com sucesso.");

	}

	private void validaEmail(String email) {
		if (clienteRepository.findByEmail(email).isPresent())
			throw new IllegalArgumentException("Email já cadastrado, tente outro.");
	}

	private void validaTelefone(String telefone) {
		if (clienteRepository.findByTelefone(telefone).isPresent())
			throw new IllegalArgumentException("Telefone já cadastrado, tente outro.");
	}

	public static String getHashMd5(String value) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
		return hash.toString(16);
	}

	public AuthenticationResponseDto auth(AuthenticationRequestDto dto) {
		Optional<Cliente> clienteOptional = this.clienteRepository.findByEmailAndSenha(dto.getEmail(), getHashMd5(dto.getSenha()));
		if (clienteOptional.isPresent()) {
			Cliente cliente = clienteOptional.get();
			AuthenticationResponseDto response = new AuthenticationResponseDto("Usuário autenticado",
					this.tokenService.generateToken(cliente.getEmail()), cliente);
			
			return response;
		}
		throw new IllegalArgumentException("Usuário não encontrado. Dados inválidos");
	}
}
