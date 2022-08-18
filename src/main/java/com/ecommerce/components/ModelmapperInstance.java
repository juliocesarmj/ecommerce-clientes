package com.ecommerce.components;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelmapperInstance {
	
	public ModelMapper mapper() {
		return new ModelMapper();
	}
}
