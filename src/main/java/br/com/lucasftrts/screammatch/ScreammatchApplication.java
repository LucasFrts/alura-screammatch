package br.com.lucasftrts.screammatch;

import br.com.lucasftrts.screammatch.main.Main;
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
		Main main = new Main();
		main.start();
	}
}
