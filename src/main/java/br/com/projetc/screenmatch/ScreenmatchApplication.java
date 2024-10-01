package br.com.projetc.screenmatch;

import br.com.projetc.screenmatch.model.DadosEpisodio;
import br.com.projetc.screenmatch.model.DadosSerie;
import br.com.projetc.screenmatch.model.DadosTemporada;
import br.com.projetc.screenmatch.service.ConsumoApi;
import br.com.projetc.screenmatch.service.ConvertDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	String apiKey = "7760cadf";
	String busca = "gilmore girls";
	String endereco = "https://www.omdbapi.com/?apikey=" + apiKey + "&t=" + busca.replace(" ", "+");

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ConsumoApi consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados(endereco);
		System.out.println("Série:" + json);

		ConvertDados convertDados = new ConvertDados();
		DadosSerie dadosSerie = convertDados.obterDados(json, DadosSerie.class);
		System.out.println(dadosSerie+"\n");

		endereco = "https://www.omdbapi.com/?apikey=" + apiKey + "&t=" + busca.replace(" ", "+") + "&season=1&episode=2";
		json = consumoApi.obterDados(endereco);
		System.out.println("Episodio:" + json);
		DadosEpisodio dadosEpisodio = convertDados.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio+"\n");

		List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i < dadosSerie.totalTemporadas(); i++) {
			endereco = "https://www.omdbapi.com/?apikey=" + apiKey + "&t=" + busca.replace(" ", "+") + "&season=" + i;
			json = consumoApi.obterDados(endereco);
			DadosTemporada dadosTemporada = convertDados.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);
	}
}
