package com.example.demo;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MovieController {
    MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @GetMapping("/")
    public List<Movie> getAll(){
        return movieRepository.findAll();
    }

    @PostMapping("/")
    public void newMovies(@RequestBody Movie movie){
        movieRepository.save(movie);
    }
    public void test(){

    }
}
