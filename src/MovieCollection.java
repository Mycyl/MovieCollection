import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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

        movieList.clear();
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
                return;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private void searchTitles () {
        System.out.print("Enter a title search term: ");
        String titleSearching = scan.nextLine().toLowerCase();
        ArrayList<Movie> movies = new ArrayList<Movie>();

        insertionSortMovieListTitle(movieList);

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
        scan.nextLine();
        //if (enter.isEmpty()) {start();}
    }

    private void searchCast () {

        System.out.print("Enter a person to search for (first or last name): ");
        String castNameSearching = scan.nextLine().toLowerCase();
        ArrayList<Movie> castMembersMovie = new ArrayList<Movie>();
        ArrayList<String> castMembers = new ArrayList<String>();
        for (Movie movie : movieList) {
            String[] castInMovie = movie.getCast().toLowerCase().split("\\|");

            ArrayList<String> name = nameInCast(castInMovie, castNameSearching);
            if (!(name.size() == 0)) {
                for (int i = 0; i < name.size(); i++) {
                    castMembersMovie.add(movie);
                    castMembers.add(name.get(i));
                }
            }
        }

        insertionSortMovieListCast(castMembers, castMembersMovie);
        ArrayList<String> castMembersToPrint = removeDuplicates(castMembers);

        for (int i = 0; i < castMembersToPrint.size(); i++) {
            System.out.println(i+1 + ". " + CapitalizeFirstAndLast(castMembersToPrint.get(i)));
        }

        if (castMembersToPrint.size() == 0) {
            System.out.println("No results match your search");
            System.out.println();
            System.out.println("** Press Enter to Return to Main Menu **");
            String enter = scan.nextLine();
            if (enter.isEmpty()) {start();}
            return;
        }

        System.out.println("Which would you like to see all movies for?");
        System.out.print("Enter number: ");

        int numAccessing = scan.nextInt();
        scan.nextLine();

        String nameAccessing = castMembersToPrint.get(numAccessing - 1);
        ArrayList<Movie> castMemberMovie = new ArrayList<Movie>();

        for (Movie movie : movieList) {
            ArrayList<String> membersInCast = new ArrayList<>(Arrays.asList(movie.getCast().toLowerCase().split("\\|")));

            if (searchForNameInCast(membersInCast, nameAccessing)) {
                castMemberMovie.add(movie);
            }
        }

        insertionSortMovieListTitle(castMemberMovie);
        int counter = 1;
        for (Movie movie : castMemberMovie) {
            System.out.println(counter + ". " + movie);
            counter++;
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        Movie movieLearningAbout = castMemberMovie.get(scan.nextInt() - 1);
        scan.nextLine();
        System.out.println();
        System.out.println("Title: " + movieLearningAbout.getTitle());
        System.out.println("Runtime: " + movieLearningAbout.getRunTime() + " minutes");
        System.out.println("Directed by: " + movieLearningAbout.getDirector());
        System.out.println("Cast: " + movieLearningAbout.getCast());
        System.out.println("Overview: " + movieLearningAbout.getOverview());
        System.out.println("User rating: " + movieLearningAbout.getUserRating());System.out.println();


        System.out.println("** Press Enter to Return to Main Menu **");
        String enter = scan.nextLine();
       // if (enter.isEmpty()) {start();}


    }

    private String CapitalizeFirstAndLast (String toCapitalize) {
        String[] firstAndLast = toCapitalize.split(" ");
        String returnStr = "";
        for (String name : firstAndLast) {
            returnStr += Capitalize(name) + " ";
        }
        return returnStr;
    }

    private String Capitalize (String toCapitalize) {
        return toCapitalize.substring(0, 1).toUpperCase() + toCapitalize.substring(1);
    }

    private boolean searchForNameInCast (ArrayList<String> castNames, String name) {
        for (String castMember : castNames) {
            if (castMember.toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<String> removeDuplicates (ArrayList<String> castMembers) {
        ArrayList<String> editedArr = new ArrayList<String>();
        for (String name : castMembers) {
            if (!inList(editedArr, name)) {
                editedArr.add(name);
            }
        }
        return editedArr;
    }

    private boolean inList (ArrayList<String> castMembersPrinted, String name) {
        for (String castMember : castMembersPrinted) {
            if (castMember.equals(name)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<String> nameInCast (String[] castMembers, String castNameSearching) {
        ArrayList<String> names = new ArrayList<String>();
        for (String name : castMembers) {
            if (name.contains(castNameSearching.toLowerCase())) {
                names.add(name);
            }
        }
        return names;
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


    public static void insertionSortMovieListTitle(ArrayList<Movie> movieList) {
        for (int i = 1; i < movieList.size(); i++) {
            int j = i;
            while (!(movieList.get(j-1).getTitle().compareTo(movieList.get(j).getTitle()) < 0)) {
                swap (movieList, j - 1, j);
                j--;
                if (j == 0) {
                    break;
                }
            }
        }
    }


    public static void insertionSortMovieListCast(ArrayList<String> names, ArrayList<Movie> movies) {
        for (int i = 1; i < names.size(); i++) {
            int j = i;
            while (!(names.get(j-1).compareTo(names.get(j)) < 0)) {
                swap (movies, names, j - 1, j);
                j--;
                if (j == 0) {
                    break;
                }
            }
        }
    }


    private static void swap (ArrayList<Movie> movies, int swap1, int swap2) {
        Movie temp = movies.get(swap1);
        movies.set(swap1, movies.get(swap2));
        movies.set(swap2, temp);
    }


    private static void swap (ArrayList<Movie> movies, ArrayList<String> names, int swap1, int swap2) {
        Movie temp = movies.get(swap1);
        movies.set(swap1, movies.get(swap2));
        movies.set(swap2, temp);
        String tempName = names.get(swap1);
        names.set(swap1, names.get(swap2));
        names.set(swap2, tempName);
    }
}
