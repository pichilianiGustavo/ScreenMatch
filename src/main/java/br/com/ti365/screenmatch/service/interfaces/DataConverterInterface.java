package br.com.ti365.screenmatch.service.interfaces;

public interface DataConverterInterface {

	<T>T getDeserializedData(String json, Class<T> genericClass);
}
