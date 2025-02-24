import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MovieCollection {

    private ArrayList<Movie> movieList;
    private Scanner scan;
    private int timesRun;

    public MovieCollection () {
        movieList = new ArrayList<Movie>();
        scan = new Scanner(System.in);
        timesRun = 0;
    }

    public void start () {

        readData();

        if (timesRun == 0) {
            System.out.println("Welcome to the movie collection!");
        }
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            timesRun++;

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private void searchTitles () {
        System.out.print("Enter a title search term: ");
        String titleSearching = scan.nextLine().toLowerCase();
        ArrayList<Movie> movies = new ArrayList<Movie>();
        selectionSortMovieListTitle(movieList);
        for (Movie movie : movieList) {
            if (movie.getTitle().toLowerCase().contains(titleSearching)) {
                movies.add(movie);
            }
        }

        if (movies.size() == 0) {
            System.out.println();
            System.out.println("No movie titles match that search term!");
        } else {
            for (int i = 0; i < movies.size(); i++) {
                System.out.println(i + 1 + ". " + movies.get(i).getTitle());
            }

            System.out.println("Which movie would you like to learn more about?");
            System.out.print("Enter number: ");
            Movie movieAccessing = movies.get(scan.nextInt() - 1);
            scan.nextLine();
            System.out.println();

            System.out.println("Title: " + movieAccessing.getTitle());
            System.out.println("Runtime: " + movieAccessing.getRunTime() + " minutes");
            System.out.println("Directed by: " + movieAccessing.getDirector());
            System.out.println("Cast: " + movieAccessing.getCast());
            System.out.println("Overview: " + movieAccessing.getOverview());
            System.out.println("User rating: " + movieAccessing.getUserRating());
        }

        System.out.println("** Press Enter to Return to Main Menu **");
        String enter = scan.nextLine();
        if (enter.isEmpty()) {start();}
    }

    private void searchCast () {

    }

    private void readData() {
        // TODO: write this method: load the shopping list data from your shoppinglist.txt file and populate shoppingList.
        //  note that this method gets called immediately at the start of the "start" method;
        //  you only need to read the data in one time to populate the shoppingList arraylist
        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String title = splitData[0];
                String cast = splitData[1];
                String director = splitData[2];
                String overview = splitData[3];
                int runTime = Integer.parseInt(splitData[4]);
                double userRating = Double.parseDouble(splitData[5]);
                movieList.add(new Movie (runTime, userRating, title, cast, director, overview));
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void saveData() {
        try {
            PrintWriter dataFile = new PrintWriter("src\\movies_data.csv");
            for (Movie movie : movieList) {
                dataFile.println(movie.getTitle() + "," + movie.getCast() + "," + movie.getDirector() + "," + movie.getOverview() + "," + movie.getRunTime() + "," + movie.getUserRating());
            }
            dataFile.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void selectionSortMovieListTitle(ArrayList<Movie> words) {
        // TODO: Part B: implement me
        for (int i = 0; i < words.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < words.size(); j++) {
                if (words.get(j).getTitle().compareTo(words.get(minIndex).getTitle()) < 0) {
                    minIndex = j;
                }
            }
            Movie temp = words.get(i);
            words.set(i, words.get(minIndex));
            words.set(minIndex, temp);
        }
    }



}
