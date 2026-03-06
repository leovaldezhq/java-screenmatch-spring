package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.EpisodeData;
import br.com.alura.screenmatch.model.Series;
import br.com.alura.screenmatch.model.SeriesData;
import br.com.alura.screenmatch.model.SeasonData;
import br.com.alura.screenmatch.service.APIConsumption;
import br.com.alura.screenmatch.service.DataConverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private APIConsumption consumption = new APIConsumption();
    private DataConverter converter = new DataConverter();
    private final String URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e2f5558";
    private List<SeriesData> seriesData = new ArrayList<>();

    public void displayMenu() {
        var option = -1;
        while(option != 0) {
            var menu = """
                    1 - Search shows
                    2 - Search episodes
                    3 - List previously searched shows
                    0 - Quit
                    """;

            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchSeriesWeb();
                    break;
                case 2:
                    searchEpisodePerSeries();
                    break;
                case 3:
                    listSearchedSeries();
                    break;
                case 0:
                    System.out.println("Leaving...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void searchSeriesWeb() {
        SeriesData data = getSeriesData();
        seriesData.add(data);
        System.out.println(data);
    }

    private SeriesData getSeriesData() {
        System.out.println("Search any show: ");
        var seriesName = scanner.nextLine();
        var json = consumption.obtainData(URL + seriesName.replace(" ", "+") + API_KEY);
        SeriesData data = converter.obtainData(json, SeriesData.class);
        return data;
    }

    private void searchEpisodePerSeries(){
        SeriesData seriesData = getSeriesData();
        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= seriesData.totalSeasons(); i++) {
            var json = consumption.obtainData(URL + seriesData.title().replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData seasonData = converter.obtainData(json, SeasonData.class);
            seasons.add(seasonData);
        }
        seasons.forEach(System.out::println);
    }

    private void listSearchedSeries(){
        List<Series> series = new ArrayList<>();
        series = seriesData.stream()
                .map(d -> new Series(d))
                .collect(Collectors.toList());
        series.stream()
                .sorted(Comparator.comparing(Series::getGenre))
                .forEach(System.out::println);
    }
}