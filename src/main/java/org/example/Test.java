package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args){
        NetflixService netflixService = new NetflixService();
        netflixService.createAccount("user1", "pass_word");

        // test Movie class and some methods related in Movie
        ArrayList<String> titanicCasts = new ArrayList<>();
        titanicCasts.add("Leonardo DiCaprio");
        titanicCasts.add("Kate Winslet");
        Movie titanic = new Movie("Titanic", "Romance/Drama", 1997, 196,"USA",titanicCasts);
        netflixService.addShow(titanic);
        netflixService.addToFavorites(titanic);
        System.out.println(netflixService.getCurrentUser().viewFavorites());

        // test Serial and some methods related in Serial
        Duration friendsDuration = new Duration(20,22);
        ArrayList<String> friendsCasts = new ArrayList<>();
        friendsCasts.add("Jennifer Aniston");
        friendsCasts.add("Courteney Cox");
        Serial friends = new Serial("friends", "Sitcome", 1994,"USA" ,10, friendsDuration, friendsCasts);
        netflixService.addShow(friends);
        ArrayList<TVShow> sitcomes;
        sitcomes = netflixService.searchByGenre("Sitcome");
        for (TVShow show: sitcomes) {
            System.out.println(show);
        }

        // test rate
        netflixService.rate(friends,10);
        netflixService.logout();
        netflixService.createAccount("user2","pass_word2");
        netflixService.rate(friends, 8);
        System.out.println(friends); // rate is 9 as wll as we expected
    }
}
