package br.com.lucasftrts.screammatch;

import br.com.lucasftrts.screammatch.models.SeriesData;
import br.com.lucasftrts.screammatch.services.ApiRequest;
import br.com.lucasftrts.screammatch.services.DataConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreammatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreammatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var json = ApiRequest.getData("https://www.omdbapi.com/?apikey=2bf63c33&t=gilmore+girls");
		System.out.println(json);
		var converter = new DataConverter();
		SeriesData data = converter.getData(json, SeriesData.class);
		System.out.println(data);
	}
}
