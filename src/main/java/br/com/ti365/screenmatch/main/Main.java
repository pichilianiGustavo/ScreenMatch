package br.com.ti365.screenmatch.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.ti365.screenmatch.model.SeasonsData;
import br.com.ti365.screenmatch.model.SeriesData;
import br.com.ti365.screenmatch.service.ApiConsumer;
import br.com.ti365.screenmatch.service.DataConverterImplementation;
import br.com.ti365.screenmatch.service.interfaces.DataConverterInterface;

public class Main {
	
	private Scanner reader = new Scanner(System.in);
	private final String BASEURL = "https://www.omdbapi.com/?t=";
	private final String APIKEY = "&apikey=cf34e04";
	private ApiConsumer apiConsumer = new ApiConsumer();
	private DataConverterInterface converter = new DataConverterImplementation();
	
	public void showMenu() {
		System.out.println("Digite o nome da serie que deseja buscar:");
		var serieName = reader.nextLine();
		var json = apiConsumer.getApiData(BASEURL + serieName.replace(" ", "+") + APIKEY);
		SeriesData seriesData = converter.getData(json, SeriesData.class);
		System.out.println(seriesData);
		
		List<SeasonsData> seasons = new ArrayList<>();
		
		for (int i = 1; i <= seriesData.seasons(); i++) {
			json = apiConsumer.getApiData(BASEURL + serieName.replace(" ", "+") + "&season=" + i +  APIKEY);
			SeasonsData seasonsData = converter.getData(json, SeasonsData.class);
			seasons.add(seasonsData);
		}
		seasons.forEach(System.out::println);
		
//		for (int i = 0; i < seriesData.seasons(); i++) {
//			List<EpisodesData> episodesSeason = seasons.get(i).episodes();
//			for (int j = 0; j < episodesSeason.size(); j++) {
//				System.out.println(episodesSeason.get(j).title());
//			}
//		}
		seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));
	}
}
