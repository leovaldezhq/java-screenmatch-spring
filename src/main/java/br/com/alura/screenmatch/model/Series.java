package br.com.alura.screenmatch.model;

import java.util.OptionalDouble;

public class Series {
    private String title;
    private Integer totalSeasons;
    private Double rating;
    private Genre genre;
    private String actors;
    private String poster;
    private String synopsis;

    public Series(SeriesData seriesData) {
        this.title = seriesData.title();
        this.totalSeasons = seriesData.totalSeasons();
        this.rating = OptionalDouble.of(Double.valueOf(seriesData.rating())).orElse(0);
        this.genre = Genre.fromString(seriesData.genre().split(",")[0].trim());
        this.actors = seriesData.actors();
        this.poster = seriesData.poster();
        this.synopsis = seriesData.synopsis();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return "Genre: " + genre +
                ", Title='" + title +
                ", Total Seasons=" + totalSeasons +
                ", Rating=" + rating +
                ", Actors='" + actors +
                ", Poster='" + poster +
                ", Synopsis='" + synopsis;
    }
}