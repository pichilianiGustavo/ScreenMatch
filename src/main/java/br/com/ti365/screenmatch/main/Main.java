package br.com.ti365.screenmatch.main;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.ti365.screenmatch.model.Episodes;
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
		
//		List<EpisodesData> episodesData = seasons.stream().flatMap(t -> t.episodes().stream()).collect(Collectors.toList());
//		episodesData.stream()
//		.filter(e -> !e.rating().equals("N/A"))
//		.peek(e -> System.out.println("Primeiro filtro de valores N/A " + e))
//		.sorted(Comparator.comparing(EpisodesData::rating).reversed())
//		.peek(e -> System.out.println("Ordenando " + e))
//		.limit(10)
//		.peek(e -> System.out.println("Limitando os 10 " + e))
//		.map(e -> e.title().toUpperCase())
//		.peek(e -> System.out.println("Todos para Upper Case " + e))
//		.forEach(e -> System.out.println(e));
		
		List<Episodes> episodes = seasons.stream().flatMap(t -> t.episodes().stream().map(e -> new Episodes(t.number(), e))).collect(Collectors.toList());
		episodes.forEach(System.out::println);
		
//		System.out.println("A partir de qual ano você deseja ver os episódios? ");
//		var ano = reader.nextInt();
//		reader.nextLine();
//		
//		LocalDate searchDate = LocalDate.of(ano, 1, 1);
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		episodes.stream()
//		.filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(searchDate))
//		.forEach(e -> System.out.println(
//				"Season: " + e.getSeason() +
//				" Episode: " + e.getTitle() +
//				" Release Date: " + e.getReleaseDate().format(dtf)
//				));
		System.out.println("Digite um trecho do episódio que deseja buscar:");
		var stringTitle = reader.nextLine();
		Optional<Episodes> searchedEpisode = episodes.stream()
		.filter(e -> e.getTitle().toUpperCase().contains(stringTitle.toUpperCase()))
		.findFirst();
		
		if(searchedEpisode.isPresent()) {
			System.out.println("Episódio encontrado: " + searchedEpisode.get());
		} else {
			System.out.println("Episódio não encontrado.");
		}
		
		Map<Integer, Double> avgRatingPerSeason = episodes.stream()
				.filter(e -> e.getRating() > 0.0)
				.collect(Collectors.groupingBy(Episodes::getSeason,Collectors.averagingDouble(Episodes::getRating)));
		System.out.println(avgRatingPerSeason);
		
		DoubleSummaryStatistics sta = episodes.stream()
				.filter(e -> e.getRating() > 0.0)
				.collect(Collectors.summarizingDouble(Episodes::getRating));
		System.out.println(sta);
		System.out.println("Média: " + sta.getAverage());
		System.out.println("Melhor episódio: " + sta.getMax());
		System.out.println("Pior episódio: " + sta.getMin());
		System.out.println("Quantidade: " + sta.getCount());
		
		}

}
