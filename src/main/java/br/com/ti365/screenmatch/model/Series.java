package br.com.ti365.screenmatch.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

import br.com.ti365.screenmatch.service.util.StringListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Series")
@Data
@NoArgsConstructor
public class Series {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "Titles", unique = true)
	private String title;
	@Column(name = "Seasons")
	private Integer seasons;
	@Column(name = "Ratings")
	private Double rating;
	@Column(name = "Genres")
	@Enumerated(EnumType.STRING)
	private Category genre;
	@Column(name = "Plots")
	private String plot;
	@Convert(converter = StringListConverter.class)
	@Column(name = "Actors")
	private List<String> actors;
	@Column(name = "Posters")
	private String posterUrl;
	@Transient
	private List<Episodes> episodios = new ArrayList<Episodes>();
	
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
	           ", atores='" + actors + '\'' +
	           ", poster='" + posterUrl + '\'' +
	           ", sinopse'" + plot + '\'' +
	           '}';
	}
	
}
