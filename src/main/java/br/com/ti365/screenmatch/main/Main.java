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

	private Scanner scanner = new Scanner(System.in);
	private final String BASEURL = "https://www.omdbapi.com/?t=";
	private final String APIKEY = "&apikey=cf34e04";
	private ApiConsumer apiConsumer = new ApiConsumer();
	private DataConverterInterface converter = new DataConverterImplementation();

	public void showMenu() {
		var menu = """
				1 - Buscar Séries
				2 - Buscar Episódios

				0 - Sair
				""";

		System.out.println(menu);
		var chosenOption = scanner.nextInt();
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
		case 0: {
			System.out.println("Saindo...");
			break;
		}
		default:
			throw new IllegalArgumentException("Opção Inválida: " + chosenOption);
		}

	}

	private void searchSeries() {
        SeriesData seriesData = getSeriesData();
        System.out.println(seriesData);
    }
    
    private SeriesData getSeriesData() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = scanner.nextLine();
        var json = apiConsumer.getApiData(BASEURL + nomeSerie.replace(" ", "+") + APIKEY);
        SeriesData seriesData = converter.getData(json, SeriesData.class);
        return seriesData;
    }
    
    private void searchEpisodesPerSeries() {
    	SeriesData dadosSerie = getSeriesData();
        List<SeasonsData> seasons = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.seasons(); i++) {
            var json = apiConsumer.getApiData(BASEURL+ dadosSerie.title().replace(" ", "+") + "&season=" + i + APIKEY);
            SeasonsData seasonsData = converter.getData(json, SeasonsData.class);
            seasons.add(seasonsData);
        }
        seasons.forEach(System.out::println);		
	}

}
