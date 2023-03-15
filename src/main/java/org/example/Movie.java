package org.example;

import java.util.ArrayList;

public class Movie extends TVShow {
    private int lengthInMinutes;

    public Movie(String title, String genre, int yearOfPublication, int lengthInMinutes,String generateCountry, ArrayList<String> casts) {
        super(title, genre, yearOfPublication,generateCountry, casts);
        this.lengthInMinutes = lengthInMinutes;
    }

    public Movie(Movie movie) {
        super(movie.getTitle(), movie.getGenre(), movie.getYearOfPublication(), movie.getGenerateCountry(), movie.getCastList());
        this.lengthInMinutes = movie.getLengthInMinutes();
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    @Override
    public String toString() {
        int hour = lengthInMinutes / 60;
        int minute = lengthInMinutes - (60 * hour);
        return super.toString() + "\n" +
                "   Length: " + ((hour > 0) ? (hour + "h ") : "") + minute + "m" + "\n";
    }
}

