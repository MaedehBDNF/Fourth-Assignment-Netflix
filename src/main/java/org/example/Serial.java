package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Serial extends TVShow {
    private int numOfSeasons;
    private ArrayList<Movie> episodes = new ArrayList<>();
    private Duration duration;

    public Serial(String title, String genre, int yearOfPublication, String generateCountry, int numOfSeasons, Duration duration, ArrayList<String> casts) {
        super(title, genre, yearOfPublication, generateCountry, casts);
        this.numOfSeasons = numOfSeasons;
        this.duration = duration;
    }

    public int getNumOfSeasons() {
        return numOfSeasons;
    }

    public void setNumOfSeasons(int numOfSeasons) {
        this.numOfSeasons = numOfSeasons;
    }

    public ArrayList<Movie> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Movie> episodes) {
        this.episodes = episodes;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void addEpisode(Movie newEpisode) {
        if (!this.episodes.contains(newEpisode)) {
            this.episodes.add(newEpisode);
            this.updateDuration(newEpisode);
            this.updateCastList(newEpisode);
        }
    }

    public void updateEpisode(Movie mainEpisode, Movie updatedEpisode) {
        int index = this.episodes.indexOf(mainEpisode);
        this.episodes.set(index, updatedEpisode);
    }

    private void updateDuration(Movie episode) {
        int episodeLength = episode.getLengthInMinutes();
        if (episodeLength > this.duration.getMaxValue()) {
            this.duration.setMaxValue(episodeLength);
        } else if (episodeLength < this.duration.getMinValue()) {
            this.duration.setMinValue(episodeLength);
        }
    }

    private void updateCastList(Movie episode) {
        for (String cast: episode.getCastList()) {
            if (!getCastList().contains(cast)) {
                getCastList().add(cast);
            }
        }
    }

    @Override
    public String toString() {
        String[] episodesTitles = new String[this.episodes.size()];
        for (int i = 0; i < this.episodes.size(); i++) {
            episodesTitles[i] = this.episodes.get(i).getTitle();
        }
        return super.toString() + "\n" +
                "   " + this.duration.toString() + "\n" +
                "   Number of seasons: " + numOfSeasons + "\n" +
                "   Episodes list: \n" + Arrays.toString(episodesTitles);
    }
}

