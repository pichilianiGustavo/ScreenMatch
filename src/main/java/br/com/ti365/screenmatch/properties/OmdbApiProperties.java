package br.com.ti365.screenmatch.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "omdb.api", ignoreUnknownFields = false)
public class OmdbApiProperties {

	private String key;
}
