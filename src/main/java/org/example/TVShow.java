package org.example;

import java.util.ArrayList;

public abstract class TVShow {
    private String title;
    private String genre;
    private double rate;
    private int yearOfPublication;
    private String generateCountry;
    private ArrayList<String> castList;
    private ArrayList<User> raters = new ArrayList<>();
    private ArrayList<User> advisers = new ArrayList<>();

    public TVShow(String title, String genre, int yearOfPublication, String generateCountry, ArrayList<String> castList) {
        this.title = title;
        this.genre = genre;
        this.rate = 0;
        this.yearOfPublication = yearOfPublication;
        this.generateCountry = generateCountry;
        this.castList = castList;
    }

    public TVShow(TVShow show) {
        this.title = show.title;
        this.genre = show.genre;
        this.rate = show.rate;
        this.yearOfPublication = show.yearOfPublication;
        this.raters = show.raters;
        this.advisers = show.advisers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public ArrayList<User> getAdvisers() {
        return advisers;
    }

    public void setAdvisers(ArrayList<User> advisers) {
        this.advisers = advisers;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getGenerateCountry() {
        return generateCountry;
    }

    public void setGenerateCountry(String generateCountry) {
        this.generateCountry = generateCountry;
    }

    public ArrayList<String> getCastList() {
        return castList;
    }

    public void setCastList(ArrayList<String> castList) {
        this.castList = castList;
    }


    // this method prevents duplicate rating
    public boolean updateRate(double newRate, User rater) {
        if (!this.raters.contains(rater)) {
            int numOfRaters = this.raters.size();
            this.rate = ((this.rate * numOfRaters) + newRate) / (numOfRaters + 1);
            this.raters.add(rater);
            return true;
        }
        return false;
    }

    public boolean recommend(User recommender){
        if (!advisers.contains(recommender)){
            return advisers.add(recommender);
        }
        return false;
    }

    @Override
    public String toString() {
        return title + ":" + "\n" +
                "   Genre: '" + genre + '\'' + "\n" +
                "   Rate: " + rate + " (Base " + raters.size() + " rates)" + "\n" +
                "   Country: " + generateCountry + "\n" +
                "   Casts list: " + castList + "\n" +
                "   Published at: " + yearOfPublication + "\n" +
                "   Users recommend this TV Show: " + advisers;
    }
}

