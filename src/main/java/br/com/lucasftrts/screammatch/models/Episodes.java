package br.com.lucasftrts.screammatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodes {
    private int season;
    private String title;
    private int number;
    private Double avaliacao;
    private LocalDate releaseDate;

    public Episodes(int numberSeason, EpisodeData episodedata) {
        this.season = numberSeason;
        this.title = episodedata.title();
        this.number = episodedata.number();
        try{
            this.avaliacao = Double.parseDouble(episodedata.avaliacao());
        }
        catch (NumberFormatException ex){
            this.avaliacao = 0.0;
        }
        try{
            this.releaseDate = LocalDate.parse(episodedata.releaseDate());
        }
        catch(DateTimeParseException ex){
            this.releaseDate = null;
        }

    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return
                "season=" + season +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", avaliacao=" + avaliacao +
                ", releaseDate=" + releaseDate;
    }
}