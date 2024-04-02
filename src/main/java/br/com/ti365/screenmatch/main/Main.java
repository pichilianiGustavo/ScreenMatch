package br.com.ti365.screenmatch.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ti365.screenmatch.model.SeasonsData;
import br.com.ti365.screenmatch.model.Series;
import br.com.ti365.screenmatch.model.SeriesData;
import br.com.ti365.screenmatch.repository.SeriesRepository;
import br.com.ti365.screenmatch.service.ApiConsumer;
import br.com.ti365.screenmatch.service.DataConverterImplementation;
import br.com.ti365.screenmatch.service.interfaces.DataConverterInterface;

public class Main {

	private Scanner scanner = new Scanner(System.in);
	private final String BASEURL = "https://www.omdbapi.com/?t=";
	private final String APIKEY = "&apikey=cf34e04";
	private ApiConsumer apiConsumer = new ApiConsumer();
	private DataConverterInterface converter = new DataConverterImplementation();
	private List<SeriesData> seriesDataList = new ArrayList<>();
	@Autowired
	private SeriesRepository seriesRepository;

	public Main(SeriesRepository seriesRepository) {
		this.seriesRepository = seriesRepository;
	}

	public void showMenu() {
		var chosenOption = -1;
		while (chosenOption != 0) {

			var menu = """
					\nEscolha uma das opções abaixo:
					\n1 - Buscar Séries
					2 - Buscar Episódios
					3 - Listar séries pesquisadas

					0 - Sair
					""";

			System.out.println(menu);
			chosenOption = scanner.nextInt();
			scanner.nextLine();

			switch (chosenOption) {
			case 1: {
				searchSeries();
				break;
			}
			case 2: {
				searchEpisodesPerSeries();
				break;
			}
			case 3: {
				listSearchedSeries();
				break;
			}
			case 0: {
				System.out.println("Saindo...");
				break;
			}
			default:
				throw new IllegalArgumentException("Opção Inválida: " + chosenOption);
			}
		}
	}

	private void searchSeries() {
		SeriesData seriesData = getSeriesData();
		//seriesDataList.add(seriesData);
		Series series = new Series(seriesData);
		seriesRepository.save(series);
		System.out.println(series);
		
		System.out.println(seriesDataList);
	}

	private SeriesData getSeriesData() {
		System.out.println("Digite o nome da série que deseja pesquisar: ");
		var nomeSerie = scanner.nextLine();
		var json = apiConsumer.getApiData(BASEURL + nomeSerie.replace(" ", "+") + APIKEY);
		SeriesData seriesData = converter.getDeserializedData(json, SeriesData.class);
		return seriesData;
	}

	private void searchEpisodesPerSeries() {
		SeriesData dadosSerie = getSeriesData();
		List<SeasonsData> seasons = new ArrayList<>();

		for (int i = 1; i <= dadosSerie.seasons(); i++) {
			var json = apiConsumer.getApiData(BASEURL + dadosSerie.title().replace(" ", "+") + "&season=" + i + APIKEY);
			SeasonsData seasonsData = converter.getDeserializedData(json, SeasonsData.class);
			seasons.add(seasonsData);
		}
		seasons.forEach(System.out::println);
	}
	
	private void listSearchedSeries() {
		List<Series> seriesList = new ArrayList<Series>();
		seriesList = seriesRepository.findAll();
		seriesList.stream()
		.forEach(System.out::println);
	}	
}
