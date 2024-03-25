package br.com.ti365.screenmatch.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ti365.screenmatch.service.interfaces.DataConverterInterface;

@Service
public class DataConverterImplementation implements DataConverterInterface {

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public <T> T getData(String json, Class<T> genericClass) {
		try {
			return mapper.readValue(json, genericClass);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
