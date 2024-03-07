package br.com.ti365.screenmatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ti365.screenmatch.model.EpisodesData;
import br.com.ti365.screenmatch.model.SeasonsData;
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
		json = apiConsumer.getApiData("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=cf34e04");
		EpisodesData episodeData = converter.getData(json, EpisodesData.class);
		System.out.println(episodeData);
		
		List<SeasonsData> seasons = new ArrayList<>();
		
		for (int i = 1; i <= seriesData.seasons(); i++) {
			json = apiConsumer.getApiData("https://www.omdbapi.com/?t=gilmore+girls&season=" + i +"&apikey=cf34e04");
			SeasonsData seasonsData = converter.getData(json, SeasonsData.class);
			seasons.add(seasonsData);
		}
		seasons.forEach(System.out::println);
	}

}
