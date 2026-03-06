package br.com.alura.screenmatch.model;

public enum Genre {
    ACTION("Action"),
    ROMANCE("Romance"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime");

    private String omdbGenre;

    Genre(String omdbGenre){
        this.omdbGenre = omdbGenre;
    }

    public static Genre fromString(String text) {
        for (Genre genre : Genre.values()) {
            if (genre.omdbGenre.equalsIgnoreCase(text)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Genre not found: " + text);
    }
}