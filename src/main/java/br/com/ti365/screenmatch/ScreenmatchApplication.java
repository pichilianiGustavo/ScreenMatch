package br.com.ti365.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ti365.screenmatch.main.Main;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		List<SeasonsData> seasons = new ArrayList<>();
//		
//		for (int i = 1; i <= seriesData.seasons(); i++) {
//			json = apiConsumer.getApiData("https://www.omdbapi.com/?t=gilmore+girls&season=" + i +"&apikey=cf34e04");
//			SeasonsData seasonsData = converter.getData(json, SeasonsData.class);
//			seasons.add(seasonsData);
//		}
//		seasons.forEach(System.out::println);
		
		Main mainClass = new Main();
		mainClass.showMenu();
	}

}
