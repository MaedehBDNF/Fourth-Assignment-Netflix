package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static NetflixService netflixService = new NetflixService();
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);

        // create a little Database for netflixService
        // add one movie
        ArrayList<String> titanicCasts = new ArrayList<>();
        titanicCasts.add("Leonardo DiCaprio");
        titanicCasts.add("Kate Winslet");
        Movie titanic = new Movie("Titanic", "Drama", 1997, 196,"USA",titanicCasts);
        netflixService.addShow(titanic);

        // add one serial
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

        // add one user for test with login
        netflixService.createAccount("default user", "1249!@hfy");

        // start interactive menu with user
        startMenu();

    }

    private static void startMenu(){
        System.out.println("Welcome to Netflix! \n" +
                "   1. LogIn\n" +
                "   2. SignUp\n" +
                "   3. Exit ");
        short choice = in.nextShort();
        in.nextLine();
        switch (choice){
            case 1:
                loginMenu();
                break;
            case 2:
                signUpMenu();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Enter a number in range 0 - 2 ");
                startMenu();
        }

    }

    private static void loginMenu() {
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            startMenu();
        }
        System.out.print("Please Enter your username: ");
        String username = in.nextLine();
        System.out.print("Please Enter your password: ");
        String password = in.nextLine();
        if (netflixService.findUser(username) != null){
            if (netflixService.login(username, password)){
                System.out.println("You log in Successfully!");
                userMenu();
            } else {
                System.out.println("Sorry your password is wrong. Please try again.");
                loginMenu();
            }
        } else {
            System.out.println("Sorry. The username is incorrect. Try again.");
            loginMenu();
        }
    }

    private static void signUpMenu() {
        System.out.print("Enter '0' for back and just enter for continue: ");
        if (in.nextLine().equals("0")) {
            startMenu();
        }
        System.out.print("Please Enter your username: ");
        String username = in.nextLine();
        System.out.print("Please Enter your password: ");
        String password = in.nextLine();
        if (netflixService.createAccount(username, password)){
            System.out.println("You signed up successfully!");
            userMenu();
        } else {
            System.out.println("Sorry! This username was taken. Try with another one.");
            signUpMenu();
        }
    }

    private static void userMenu() {
        System.out.println("What do you want to do? \n" +
                "   1. Search by title \n" +
                "   2. Search by genre \n" +
                "   3. Search by year of publication \n" +
                "   4. Search by title in favorites and history \n" +
                "   5. Search by genre in favorites and history \n" +
                "   6. Search by year of publication in favorites and history \n" +
                "   7. View favorite shows \n" +
                "   8. View history of shows\n" +
                "   9. View recommended shows\n" +
                "   10. Logout");
        short choice = in.nextShort();
        in.nextLine();
        if (1 <= choice && choice <= 10) {
            switch (choice) {
                case 1:
                    System.out.println("Enter the name of movie or serial: ");
                    String title = in.nextLine();
                    ArrayList<TVShow> foundShows = netflixService.searchByTitle(title);
                    presentationMenu(foundShows);
                    break;
                case 2:
                    System.out.println("Enter the genre of movie or serial: ");
                    String genre = in.nextLine();
                    foundShows = netflixService.searchByGenre(genre);
                    presentationMenu(foundShows);
                    break;
                case 3:
                    System.out.println("Enter the year of publication of movie or serial: ");
                    int year = in.nextInt();
                    foundShows = netflixService.searchByReleaseYear(year);
                    presentationMenu(foundShows);
                    break;
                case 4:
                    System.out.println("Enter the name of movie or serial: ");
                    title = in.nextLine();
                    foundShows = netflixService.getCurrentUser().searchByTitle(title);
                    if (foundShows.size() == 0){
                        System.out.println("This show doesn't exist in your favorites and history.\n Now");
                        userMenu();
                    } else {
                        presentationMenu(foundShows);
                    }
                    break;
                case 5:
                    System.out.println("Enter the genre of movie or serial: ");
                    genre = in.nextLine();
                    foundShows = netflixService.getCurrentUser().searchByGenre(genre);
                    if (foundShows.size() == 0){
                        System.out.println("There is no any show with this genre in your favorites and history.\n Now");
                        userMenu();
                    } else {
                        presentationMenu(foundShows);
                    }
                    break;
                case 6:
                    System.out.println("Enter the year of publication of movie or serial: ");
                    year = in.nextInt();
                    foundShows = netflixService.getCurrentUser().searchByReleaseYear(year);
                    if (foundShows.size() == 0){
                        System.out.println("There is no any show with this release year in your favorites and history.\n Now");
                        userMenu();
                    } else {
                        presentationMenu(foundShows);
                    }
                    break;
                case 7:
                    if (netflixService.getCurrentUser().viewFavorites().size() == 0) {
                        System.out.println("There is not any show.");
                        userMenu();
                    } else {
                        presentationMenu(netflixService.getCurrentUser().viewFavorites());
                    }
                    break;
                case 8:
                    if (netflixService.getCurrentUser().viewWatchHistory().size() == 0) {
                        System.out.println("There is not any show.");
                        userMenu();
                    } else {
                        presentationMenu(netflixService.getCurrentUser().viewWatchHistory());
                    }
                    break;
                case 9:
                    if (netflixService.getCurrentUser().getRecommendations().size() == 0) {
                        System.out.println("There is not any show.");
                        userMenu();
                    } else {
                        presentationMenu(netflixService.getCurrentUser().getRecommendations());
                    }
                    break;
                case 10:
                    netflixService.logout();
                    startMenu();
            }
        } else {
            System.out.println("Enter a number in range 1 - 10");
            userMenu();
        }
    }

    // presents a given list of shows
    private static void presentationMenu(ArrayList<TVShow> shows) {
        System.out.println("found shows:");
        for (int i = 0; i < shows.size(); i++){
            System.out.println((i+1) + ") " + shows.get(i).getTitle());
        }
        System.out.println("select one of above or enter 0 for back: ");
        short choice;
        do {
            choice = in.nextShort();
            if  (choice == 0){
                userMenu();
            } else if (choice >= 1 && choice <= shows.size()){
                if(shows.get(choice -1).getClass().getSimpleName().equals("Movie")){
                    movieMenu((Movie) shows.get(choice - 1));
                } else if (shows.get(choice - 1).getClass().getSimpleName().equals("Serial")) {
                    serialMenu((Serial) shows.get(choice - 1));
                }
            } else {
                System.out.println("Enter a number in range 0 - " + shows.size());
            }
        } while (choice < 0 || choice > shows.size());
    }

    private static void movieMenu(Movie movie) {
        System.out.println("\nMovie information: \n");
        System.out.println(movie);
        System.out.println("\nWhat do you want to do? \n" +
                "   1. Watch movie \n" +
                "   2. Rate movie \n" +
                "   3. Add movie to favorites \n" +
                "   4. Recommend movie \n" +
                "   0. back to menu");

        short choice = in.nextShort();
        in.nextLine();
        if (0 <= choice && choice <= 4) {
            switch (choice) {
                case 0:
                    userMenu();
                    break;
                case 1:
                    netflixService.watch(movie);
                    System.out.println("We wish you have enjoyed watching this Movie. :) \n Now");
                    movieMenu(movie);
                    break;
                case 2:
                    System.out.println("Rate this movie from 1 to 10. \nYour rate: ");
                    short newRate;
                    do {
                        newRate = in.nextShort();
                        in.nextLine();
                        if (1 <= newRate && newRate <= 10) {
                            netflixService.rate(movie, newRate);
                        } else {
                            System.out.println("You should enter a number in range 1 - 10.");
                        }
                    } while (newRate < 1 || newRate > 10);
                    System.out.println("You rate " + movie.getTitle() + " successfully. \n Now");
                    movieMenu(movie);
                    break;
                case 3:
                    netflixService.addToFavorites(movie);
                    System.out.println(movie.getTitle() + " just added to your favorites. \n Now");
                    movieMenu(movie);
                    break;
                case 4:
                    netflixService.recommend(movie);
                    System.out.println(movie.getTitle() + " just added to your recommendations.\n Now");
                    movieMenu(movie);
                    break;
            }
        } else {
            System.out.println("Enter a number in range 0 - 4.");
            movieMenu(movie);
        }
    }

    private static void serialMenu(Serial serial) {
        System.out.println("\nSerial information: \n");
        System.out.println(serial);
        System.out.println("\nWhat do you want to do? \n" +
                "   1. Watch serial \n" +
                "   2. Rate serial \n" +
                "   3. Add serial to favorites \n" +
                "   4. Recommend serial \n" +
                "   5. Show list of episodes \n" +
                "   0. back to menu");

        short choice = in.nextShort();
        in.nextLine();
        if (0 <= choice && choice <= 5) {
            switch (choice) {
                case 0:
                    userMenu();
                    break;
                case 1:
                    netflixService.watch(serial);
                    System.out.println("We wish you have enjoyed watching this Movie. :) \n Now");
                    serialMenu(serial);
                    break;
                case 2:
                    System.out.println("Rate this movie from 1 to 10. \nYour rate: ");
                    short newRate;
                    do {
                        newRate = in.nextShort();
                        in.nextLine();
                        if (1 <= newRate && newRate <= 10) {
                            netflixService.rate(serial, newRate);
                        } else {
                            System.out.println("You should enter a number in range 1 - 10.");
                        }
                    } while (newRate < 1 || newRate > 10);
                    System.out.println("You rate " + serial.getTitle() + " successfully. \n Now");
                    serialMenu(serial);
                    break;
                case 3:
                    netflixService.addToFavorites(serial);
                    System.out.println(serial.getTitle() + " just added to your favorites.\n Now");
                    serialMenu(serial);
                    break;
                case 4:
                    netflixService.recommend(serial);
                    System.out.println(serial.getTitle() + " just added to your recommendations.\n Now");
                    serialMenu(serial);
                    break;
                case 5:
                    serialPresenterMenu(serial);
                    break;
            }
        } else {
            System.out.println("Enter a number in range 0 - 5.");
            serialMenu(serial);
        }
    }

    // presents a serial with its episodes
    private static void serialPresenterMenu(Serial serial) {
        ArrayList<Movie> episodes = serial.getEpisodes();
        System.out.println();
        System.out.println();
        System.out.println("Episodes of " + serial.getTitle() + ":");
        for (int i = 0; i < episodes.size(); i++){
            System.out.println((i+1) + ")" + episodes.get(i).getTitle());
        }

        System.out.println("Enter the number of episode: ");
        int episodeNum = in.nextInt();
        in.nextLine();
        if (1 <= episodeNum && episodeNum <= episodes.size()) {
            episodeMenu(serial, serial.getEpisodes().get(episodeNum - 1));
        } else {
            System.out.println("Wrong number! try again.");
            serialPresenterMenu(serial);
        }
    }

    private static void episodeMenu(Serial serial, Movie episode) {
        System.out.println(serial.getTitle() + ":\n");
        System.out.println(episode);
        System.out.println("\nWhat do you want to do? \n" +
                "   1. Watch this episode \n" +
                "   2. Rate this episode \n" +
                "   3. Add this episode to favorites \n" +
                "   4. Recommend this episode \n" +
                "   0. back to menu");

        short choice = in.nextShort();
        in.nextLine();
        if (0 <= choice && choice <= 4) {
            switch (choice) {
                case 0:
                    userMenu();
                    break;
                case 1:
                    netflixService.watch(episode);
                    System.out.println("We wish you have enjoyed watching this Movie. :) \n Now");
                    serialMenu(serial);
                    break;
                case 2:
                    System.out.println("Rate this movie from 1 to 10. \nYour rate: ");
                    short newRate;
                    do {
                        newRate = in.nextShort();
                        in.nextLine();
                        if (1 <= newRate && newRate <= 10) {
                            netflixService.rate(serial ,episode, newRate);
                        } else {
                            System.out.println("You should enter a number in range 1 - 10.");
                        }
                    } while (newRate < 1 || newRate > 10);
                    System.out.println("You rate " + episode.getTitle() + " successfully. \n Now");
                    serialMenu(serial);
                    break;
                case 3:
                    netflixService.addToFavorites(episode);
                    System.out.println(episode.getTitle() + " just added to your favorites. \n Now");
                    serialMenu(serial);
                    break;
                case 4:
                    netflixService.recommend(serial, episode);
                    System.out.println(episode.getTitle() + " just added to your recommendations. \n Now");
                    serialMenu(serial);
                    break;
            }
        } else {
            System.out.println("Enter a number in range 0 - 4.");
            episodeMenu(serial, episode);
        }
    }
}