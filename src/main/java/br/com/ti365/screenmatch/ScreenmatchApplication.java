package br.com.ti365.screenmatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ti365.screenmatch.main.Main;
import br.com.ti365.screenmatch.repository.SeriesRepository;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{
	
	@Autowired
	private SeriesRepository seriesRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main mainClass = new Main(seriesRepository);
		mainClass.showMenu();
	}

}
