package br.com.lucasftrts.screammatch.main;

import br.com.lucasftrts.screammatch.models.EpisodeData;
import br.com.lucasftrts.screammatch.models.SeasonData;
import br.com.lucasftrts.screammatch.models.SeriesData;
import br.com.lucasftrts.screammatch.services.ApiRequest;
import br.com.lucasftrts.screammatch.services.DataConverter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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



    }

}
