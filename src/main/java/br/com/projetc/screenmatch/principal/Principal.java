package br.com.projetc.screenmatch.principal;

import br.com.projetc.screenmatch.model.DadosEpisodio;
import br.com.projetc.screenmatch.model.DadosSerie;
import br.com.projetc.screenmatch.model.DadosTemporada;
import br.com.projetc.screenmatch.service.ConsumoApi;
import br.com.projetc.screenmatch.service.ConvertDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    Scanner scanner = new Scanner(System.in);
    private final String API_KEY = "&apikey=7760cadf";
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConvertDados convertDados = new ConvertDados();


    public void exibirMenu() {
        System.out.println("Digite o nome da série para busca: ");
        String nomeSerie = scanner.nextLine().replace(" ", "+");
        String json = consumoApi.obterDados(ENDERECO + nomeSerie + API_KEY);

        DadosSerie dadosSerie = convertDados.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            json = consumoApi.obterDados(ENDERECO + nomeSerie + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = convertDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);

        temporadas.forEach(temporada -> temporada.episodios().forEach(episodio -> System.out.println(episodio.titulo())));
    }
}