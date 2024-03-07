package br.com.ti365.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ti365.screenmatch.model.SeriesData;
import br.com.ti365.screenmatch.service.ApiConsumer;
import br.com.ti365.screenmatch.service.DataConverterImplementation;
import br.com.ti365.screenmatch.service.interfaces.DataConverterInterface;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ApiConsumer apiConsumer = new ApiConsumer();
		var json = apiConsumer.getApiData("https://www.omdbapi.com/?t=gilmore+girls&apikey=cf34e04");
		System.out.println(json);
		DataConverterInterface converter = new DataConverterImplementation();
		SeriesData seriesData = converter.getData(json, SeriesData.class);
		System.out.println(seriesData);
		
	}

}
