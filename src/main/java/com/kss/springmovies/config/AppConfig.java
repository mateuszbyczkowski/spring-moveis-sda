package com.kss.springmovies.config;

import com.kss.springmovies.dto.Movie;
import com.kss.springmovies.repository.EntityExistsException;
import com.kss.springmovies.repository.MovieRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MovieRepository getMovieRepository() throws EntityExistsException {
        MovieRepository movieRepository = new MovieRepository();
        movieRepository.addMovie(new Movie("American Beauty"));

        return movieRepository;
    }
}
