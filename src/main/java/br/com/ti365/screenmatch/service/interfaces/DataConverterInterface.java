package br.com.ti365.screenmatch.service.interfaces;

public interface DataConverterInterface {

	<T>T getData(String json, Class<T> genericClass);
}
