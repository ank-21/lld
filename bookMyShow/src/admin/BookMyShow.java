package admin;

import model.City;
import model.Movie;
import model.Theatre;

import java.util.*;

public class BookMyShow {
    private static BookMyShow bms;
    private List<City> cities;
    private HashMap<City, List<Theatre>> cityVsTheatre;
    private HashMap<City, List<Movie>> cityVsMovies;


    private BookMyShow(){
        this.cityVsTheatre = new HashMap<>();
        this.cityVsMovies = new HashMap<>();
        cities = new ArrayList<>();
    }

    public static BookMyShow getInstance(){
        if(bms == null)
            bms = new BookMyShow();
        return bms;
    }

    public HashMap<City, List<Theatre>> getCityVsTheatre() {
        return cityVsTheatre;
    }

    protected void addTheatreInCity(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the following details for adding theatre in the city");
        System.out.print("Please enter the city name : ");
        String cityName = sc.nextLine();

        City userCity = null;
        //checking if city is already created

        for (City city : cities){
            if(cityName.equalsIgnoreCase(city.getCityName())){
                userCity = city;
                break;
            }
        }

        if(userCity == null) {
            System.out.print("Please enter the state name : ");
            String state = sc.nextLine();
            userCity = new City(cityName, state);
            cities.add(userCity);
        }

        System.out.print("Please enter the theatre name : ");
        String theatreName = sc.nextLine();

        System.out.print("Please enter the number of screens : ");
        int screenCount = sc.nextInt();
        sc.nextLine();

        Theatre theatre = new Theatre(theatreName, screenCount);
        List<Theatre> theatreList = cityVsTheatre.getOrDefault(userCity, new ArrayList<>());
        //Assuming user provides a new theatre for a city everytime, otherwise throw exception from here
        theatreList.add(theatre);
        cityVsTheatre.put(userCity, theatreList);
    }

    protected void addMovie(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the movie name : ");
        String name = sc.nextLine();

        System.out.print("Please enter the number of cast : ");
        int castCount = sc.nextInt();
        sc.nextLine();
        List<String> cast = new ArrayList<>();

        while(castCount > 0){
            System.out.print("Please enter the cast name : ");
            String castName = sc.nextLine();
            cast.add(castName);
            castCount--;
        }

        UUID id = UUID.randomUUID();

        System.out.print("Please enter the movie duration in minutes : ");
        int time = sc.nextInt();
        sc.nextLine();

        System.out.print("Please enter the base price of the movie ticket : ");
        int price = sc.nextInt();
        sc.nextLine();

        Movie movie = new Movie(name, cast, id, time, price);

        addMovieInCity(movie);

    }
    private void addMovieInCity(Movie movie){
        Scanner sc = new Scanner(System.in);
        for(City city : cities){
            List<Theatre> theatreList = cityVsTheatre.get(city);
            System.out.println("Please select the theatre in the city " + city.getCityName() + " to add the movies by adding Y/N");
            for(Theatre theatre : theatreList){
                System.out.print(theatre.getTheatreName() + " Y/N : ");
                String choice = sc.nextLine();
                if("Y".equalsIgnoreCase((choice))){
                    List<Theatre> theatreListForMovie = city.getMovieVsTheatreList().getOrDefault(movie, new ArrayList<>());
                    theatreListForMovie.add(theatre);
                    city.getMovieVsTheatreList().put(movie, theatreListForMovie);
                    theatre.addShowsForMovie(movie);

                    //This is for adding movie in the city key hashmap
                    List<Movie> movieListForCity = this.cityVsMovies.getOrDefault(city, new ArrayList<>());
                    if(!movieListForCity.contains(movie)) {
                        movieListForCity.add(movie);
                        this.cityVsMovies.put(city, movieListForCity);
                    }
                }
            }
        }
    }

    protected void showMoviesList(City userAskedCity){
        List<Movie> movieListInCity = cityVsMovies.get(userAskedCity);
        for(Movie movie : movieListInCity){
            System.out.println("Movie name : " + movie.getMovieName());
            System.out.println("Starring : " + String.join(", ", movie.getCast()));
            System.out.println("Movie duration : " + movie.getMovieDurationInMin() + " minutes");
            System.out.println("------------------------------------");
        }
    }

    protected City verifyCity(String userCity){
        for (City city : cities) {
            if (userCity.equalsIgnoreCase(city.getCityName())) {
                return city;
            }
        }
        System.out.println("There are no movies in this city!");
        return null;
    }

    protected Movie validateMovie(String movieName, City city){
        List<Movie> movieListInCity = cityVsMovies.get(city);
        for(Movie movie : movieListInCity){
            if(movieName.equalsIgnoreCase(movie.getMovieName())){
                return movie;
            }
        }
        System.out.println("Please select a movie from the selected city");
        return null;
    }
}
