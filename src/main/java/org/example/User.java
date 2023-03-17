package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String password;
    private ArrayList<TVShow> favorites = new ArrayList<>();
    private ArrayList<TVShow> watchHistory = new ArrayList<>();
    private ArrayList<TVShow> recommendedList = new ArrayList<>();

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<TVShow> searchByTitle(String title) {
        ArrayList<TVShow> result = new ArrayList<>();
        Set<TVShow> favoritesAndHistory = new HashSet<>();
        favoritesAndHistory.addAll(favorites);
        favoritesAndHistory.addAll(watchHistory);

        for(TVShow show: favoritesAndHistory) {
            if (show.getTitle().equalsIgnoreCase(title)) {
                result.add(show);
            }
        }
        return result;
    }

    public ArrayList<TVShow> searchByGenre(String genre) {
        ArrayList<TVShow> result = new ArrayList<>();
        Set<TVShow> favoritesAndHistory = new HashSet<>();
        favoritesAndHistory.addAll(favorites);
        favoritesAndHistory.addAll(watchHistory);

        for(TVShow show: favoritesAndHistory) {
            if (show.getGenre().equalsIgnoreCase(genre)) {
                result.add(show);
            }
        }
        return result;
    }

    public ArrayList<TVShow> searchByReleaseYear(int year) {
        ArrayList<TVShow> result = new ArrayList<>();
        Set<TVShow> favoritesAndHistory = new HashSet<>();
        favoritesAndHistory.addAll(favorites);
        favoritesAndHistory.addAll(watchHistory);

        for(TVShow show: favoritesAndHistory) {
            if (show.getYearOfPublication() == year) {
                result.add(show);
            }
        }
        return result;
    }

    public void addToFavorites(TVShow show) {
        if (!this.favorites.contains(show)) {
            this.favorites.add(show);
        }
    }

    public ArrayList<TVShow> viewFavorites() {
        return this.favorites;
    }

    public void watch(TVShow show) {
        if (!this.watchHistory.contains(show)) {
            this.watchHistory.add(show);
        }
    }

    public ArrayList<TVShow> viewWatchHistory() {
        return this.watchHistory;
    }

    public void recommend(TVShow show) {
        if(!this.recommendedList.contains(show)) {
            this.recommendedList.add(show);
        }
    }

    public ArrayList<TVShow> getRecommendations() {
        return this.recommendedList;
    }

    // for updating all lists containing a specific TVShow after change it and if it there is not exist returns false
    public boolean updateTVShow(TVShow mainTVShow, TVShow updatedTVShow) {
        int index = this.favorites.indexOf(mainTVShow);
        if (index != -1) {
            this.favorites.set(index, updatedTVShow);
            return true;
        }
        index = this.watchHistory.indexOf(mainTVShow);
        if (index != -1) {
            this.watchHistory.set(index, updatedTVShow);
            return true;
        }
        index = this.recommendedList.indexOf(mainTVShow);
        if (index != -1) {
            this.recommendedList.set(index, updatedTVShow);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Username: " + username;
    }
}
