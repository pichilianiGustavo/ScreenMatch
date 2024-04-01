package br.com.ti365.screenmatch.model;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

import lombok.Data;

@Data
public class Series {
	
	private String title;
	private Integer seasons;
	private Double rating;
	private Category genre;
	private String plot;
	private List<String> actors;
	private String posterUrl;

	public Series(SeriesData seriesData) {
		this.title = seriesData.title();
		this.seasons = seriesData.seasons();
		this.rating = OptionalDouble.of(Double.valueOf(seriesData.rating())).orElse(0);
		this.genre = Category.fromString(seriesData.genre().split(",")[0]);
		this.actors = Arrays.asList(seriesData.actors().split(","));
		this.plot = seriesData.plot();
		this.posterUrl = seriesData.posterUrl();
	}

	@Override
	public String toString() {
	    return "Serie{" +
	           "genero=" + genre +
	           ", titulo='" + title + '\'' +
	           ", totalTemporadas=" + seasons +
	           ", avaliacao=" + rating +
	           ", atores='" + rating + '\'' +
	           ", poster='" + posterUrl + '\'' +
	           ", sinopse'" + plot + '\'' +
	           '}';
	}
	
}
