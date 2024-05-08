package br.com.lucasftrts.screammatch.main;

import br.com.lucasftrts.screammatch.models.EpisodeData;
import br.com.lucasftrts.screammatch.models.Episodes;
import br.com.lucasftrts.screammatch.models.SeasonData;
import br.com.lucasftrts.screammatch.models.SeriesData;
import br.com.lucasftrts.screammatch.services.ApiRequest;
import br.com.lucasftrts.screammatch.services.DataConverter;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner reader = new Scanner(System.in);
    private final String URL = "https://www.omdbapi.com?";
    private final String API_KEY = "&apikey=2bf63c33&";

    private DataConverter converter = new DataConverter();

    public void start() throws IOException, InterruptedException {
        System.out.println("Digite o nome da série para buscar");
        var serieName = reader.nextLine();

        var path = URL + "t=" + URLEncoder.encode(serieName)  + API_KEY;
        var json = ApiRequest.getData(path);
        SeriesData seriesdata = converter.getData(json, SeriesData.class);
        System.out.println(seriesdata);

        List<SeasonData> seasons = new ArrayList<>();
		for(int i = 1; i <= seriesdata.totalSeasons();i++){
			json = ApiRequest.getData(path.concat("&season="+ i));
			SeasonData seasonData = converter.getData(json, SeasonData.class);
			seasons.add(seasonData);
		}
//        forma de iterar o for com uma collection
//        for(SeasonData season: seasons){
//            for(EpisodeData episode: season.episodes()){
//                System.out.println("Season: "+ season.number() + " Episode:" + episode.title());
//            }
//        }
//        forma de iterar utilizando o foreach e lambda
        seasons.forEach(season -> {
            season.episodes().forEach(episode -> {
                     System.out.println("Season: "+ season.number() + " Episode:" + episode.title());
            });
        });

        List<EpisodeData> episodesData = seasons.stream().flatMap(t -> t.episodes().stream()).collect(Collectors.toList());
        System.out.println("top 5 episodios");
        episodesData.stream()
                .filter(e -> !e.avaliacao().equals("N/A"))
                .sorted(Comparator.comparing(EpisodeData::avaliacao)
                        .reversed())
                        .limit(5)
                        .forEach(System.out::println);

        List<Episodes> episodes = seasons
                    .stream()
                    .flatMap(t -> t.episodes().stream().map(d -> new Episodes(t.number(), d)))
                    .collect(Collectors.toList());

        System.out.println("digite o titulo a encontrar:\n");
        var searching = reader.nextLine();
        var episode = episodes.stream()
                .filter(e -> e.getTitle().toUpperCase().contains(searching.toUpperCase()))
                .findFirst();
        if(episode.isPresent()){
            System.out.println("episodio encontrado: ");
            System.out.println("temporada do episodio" + episode.get().getSeason());
        }else{
            System.out.println("Episodio nao encontrado");
        }

        Map<Integer, Double> seasonAvaliation = episodes
                                .stream()
                                .filter(e -> e.getAvaliacao() > 0.0)
                                .collect(Collectors.groupingBy(Episodes::getSeason, Collectors.averagingDouble(Episodes::getAvaliacao)));

        System.out.println(seasonAvaliation);

        DoubleSummaryStatistics est = episodes.stream().filter(e -> e.getAvaliacao() > 0.0).collect(Collectors.summarizingDouble(Episodes::getAvaliacao));

        System.out.println("Média " + est.getAverage() + " Melhor episodio: " + est.getMax() + " Pior episodio: " + est.getMin() + " quantidade avaliada " + est.getCount());

//        episodes.forEach(System.out::println);
//
//        System.out.println("a partir de que ano você deseja ver os episodios?");
//        var year = reader.nextInt();
//        reader.nextLine();
//
//        LocalDate dateSearch = LocalDate.of(year, 1, 1);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodes
//                .stream()
//                .filter(e ->e.getReleaseDate() != null && e.getReleaseDate().isAfter(dateSearch))
//                .forEach(e -> System.out.println("Season: " + e.getSeason()
//                        + " Episode: " + e.getNumber()
//                        + " Release date: " + e.getReleaseDate().format(formatter)));

    }

}
