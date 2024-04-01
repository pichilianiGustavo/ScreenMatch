package br.com.ti365.screenmatch.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ti365.screenmatch.service.interfaces.DataConverterInterface;

@Service
public class DataConverterImplementation implements DataConverterInterface {

	private final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public <T> T getDeserializedData(String json, Class<T> genericClass) {
		try {
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
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
