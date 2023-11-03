package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class MovieRepository {

    HashMap<String, Movie> movieDatabase = new HashMap<>();
    HashMap<String, Director> directorDatabase = new HashMap<>();
    HashMap<String, ArrayList<String>> directorMovie = new HashMap<>();

    public void addMovie(Movie movie){
        movieDatabase.put(movie.getName(), movie);
    }

    public void addDirector(Director director){
        directorDatabase.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movie, String director){
        ArrayList<String> listOfMovie = new ArrayList<>();

        if(directorMovie.containsKey(director)){
            listOfMovie = directorMovie.get(director);
        }

        listOfMovie.add(movie);
        directorMovie.put(director,listOfMovie);
    }

    public Movie getMovieByName(String movie){
        return movieDatabase.get(movie);
    }

    public Director getDirectorByName(String director){
        return directorDatabase.get(director);
    }

    public ArrayList<String> getMoviesByDirectorName(String director){
        return directorMovie.get(director);
    }

    public ArrayList<String> findAllMovies(){

        ArrayList<String> allMovie = new ArrayList<>();
        for(String movie : movieDatabase.keySet()){
            allMovie.add(movie);
        }
        return allMovie;
    }

    public void deleteDirectorByName(String director){
        directorDatabase.remove(director);
        ArrayList<String> movies = directorMovie.get(director);
        for(String movie : movies){
            movieDatabase.remove(movie);
        }
        directorMovie.remove(director);
    }

    public void deleteAllDirectors(){
        directorDatabase.clear();
        for(String director : directorMovie.keySet()){
            ArrayList<String> movies = directorMovie.get(director);
            for(String movie : movies){
                movieDatabase.remove(movie);
            }
        }

        directorMovie.clear();
    }

}
