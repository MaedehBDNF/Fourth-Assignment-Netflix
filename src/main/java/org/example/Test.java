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
        friendsCasts.add("Matthew Perry");
        friendsCasts.add("Matt LeBlanc");
        friendsCasts.add("David Schwimmer");
        friendsCasts.add("Lisa Kudrow");
        Serial friends = new Serial("Friends", "Sitcome", 1994,"USA" ,10, friendsDuration, friendsCasts);
        Movie theOneWithMrsBing = new Movie("theOneWithMrsBing", "Sitcome", 1994, 22, "USA",friendsCasts);
        Movie theOneWithAllThePoker = new Movie("theOneWithAllThePoker", "Sitcome", 1994, 22, "USA",friendsCasts);
        friends.addEpisode(theOneWithMrsBing);
        friends.addEpisode(theOneWithAllThePoker);
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

        // test recommend
        netflixService.recommend(friends, theOneWithAllThePoker);
        System.out.println(friends.getEpisodes().get(1).getAdvisers());
        System.out.println(netflixService.getCurrentUser().getRecommendations());
    }
}
