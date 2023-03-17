package org.example;

import java.util.ArrayList;


public class NetflixService {
    private User currentUser;
    private int indexOfCurrentUser;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<TVShow> shows = new ArrayList<>();

    public NetflixService() {
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void addShow(TVShow show){
        if (!this.doesShowExist(show)) {
            this.shows.add(show);
        }
    }

    public boolean createAccount(String username, String password) {
        User foundUser = findUser(username);
        if (foundUser == null) {
            this.currentUser = new User(username, password);
            this.users.add(this.currentUser);
            this.indexOfCurrentUser = this.users.indexOf(this.currentUser);
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) {
        User foundUser = findUser(username);
        if (foundUser.getPassword().equals(password)) {
            this.currentUser = foundUser;
            this.indexOfCurrentUser = this.users.indexOf(this.currentUser);
            return true;
        }
        return false;
    }

    public void logout() {
        this.currentUser = null;
        this.indexOfCurrentUser = -1;
    }

    public ArrayList<TVShow> searchByTitle(String title) {
        ArrayList<TVShow> result = new ArrayList<>();

        for(TVShow show: this.shows) {
            if (show.getTitle().equalsIgnoreCase(title)) {
                result.add(show);
            }
        }
        return result;
    }

    public ArrayList<TVShow> searchByGenre(String genre) {
        ArrayList<TVShow> result = new ArrayList<>();

        for(TVShow show: this.shows) {
            if (show.getGenre().equalsIgnoreCase(genre)) {
                result.add(show);
            }
        }
        return result;
    }

    public ArrayList<TVShow> searchByReleaseYear(int year) {
        ArrayList<TVShow> result = new ArrayList<>();

        for(TVShow show: this.shows) {
            if (show.getYearOfPublication() == year) {
                result.add(show);
            }
        }
        return result;
    }

    public void watch(TVShow show) {
        this.currentUser.watch(show);
        this.updateCurrentUser(this.currentUser);
    }

    public void addToFavorites(TVShow show) {
        this.currentUser.addToFavorites(show);
        this.updateCurrentUser(this.currentUser);
    }

    public void recommend(TVShow show) {
        TVShow mainShow;
        if (show.getClass().getSimpleName().equals("Movie")) {
            mainShow = new Movie((Movie) show);
        } else if (show.getClass().getSimpleName().equals("Serial")){
            mainShow = new Serial((Serial) show);
        } else {
            return;
        }
        int index = this.shows.indexOf(show);

        if (show.recommend(this.currentUser)){
            this.shows.set(index, show);
            this.currentUser.recommend(show);
            this.updateCurrentUser(this.currentUser);

            for(int i = 0; i < this.users.size(); i++) {
                User user = this.users.get(i);
                if (user.updateTVShow(mainShow, show)) {
                    this.users.set(i, user);
                }
            }
        }
    }

    public void recommend(Serial serial, Movie episode) {
        Serial mainSerial = new Serial(serial);
        Movie mainEpisode = new Movie(episode);
        int serialIndex = this.shows.indexOf(serial);
        if (episode.recommend(this.currentUser)){
            this.shows.set(serialIndex, serial);
            this.currentUser.recommend(episode);
            this.updateCurrentUser(this.currentUser);

            for(int i = 0; i < this.users.size(); i++) {
                User user = this.users.get(i);
                if (user.updateTVShow(mainSerial, serial) | user.updateTVShow(mainEpisode, episode)) {
                    this.users.set(i, user);
                }
            }
        }
    }

    public void rate(TVShow show, double newRate) {
        TVShow mainShow;
        if (show.getClass().getSimpleName().equals("Movie")) {
            mainShow = new Movie((Movie) show);
        } else if (show.getClass().getSimpleName().equals("Serial")){
            mainShow = new Serial((Serial) show);
        } else {
            return;
        }

        int index = this.shows.indexOf(show);
        show.updateRate(newRate, this.currentUser);

        this.shows.set(index, show);
        for(int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);
            if (user.updateTVShow(mainShow, show)) {
                this.users.set(i, user);
            }
        }
    }

    public void rate(Serial serial, Movie episode, double newRate) {
        Serial mainSerial = new Serial(serial);
        Movie mainEpisode = new Movie(episode);
        int serialIndex = this.shows.indexOf(mainSerial);

        episode.updateRate(newRate, this.currentUser);

        this.shows.set(serialIndex, serial);
        for(int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);
            if (user.updateTVShow(mainSerial, serial) | user.updateTVShow(mainEpisode, episode)) {
                this.users.set(i, user);
            }
        }
    }

    public Serial addEpisode(Serial serial, Movie episode) {
        Serial mainSerial = new Serial(serial);
        int serialIndex = this.shows.indexOf(mainSerial);

        serial.addEpisode(episode);

        this.shows.set(serialIndex, serial);

        for(int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);
            if (user.updateTVShow(mainSerial, serial)) {
                this.users.set(i, user);
            }
        }
        return serial;
    }

    private void updateCurrentUser(User updatedUser) {
        this.currentUser = updatedUser;
        this.users.set(this.indexOfCurrentUser, updatedUser);
    }

    public User findUser(String username) {
        for (User user: this.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private boolean doesShowExist(TVShow show) {
        for (TVShow showElement: this.shows) {
            if (showElement.equals(show)) {
                return true;
            }
        }
        return false;
    }
}

