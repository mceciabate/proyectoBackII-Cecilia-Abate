package com.digitalmedia.movies.controller;

import com.digitalmedia.movies.mapper.MovieMapper;
import com.digitalmedia.movies.model.Movie;
import com.digitalmedia.movies.model.dto.AddCommentRequest;
import com.digitalmedia.movies.model.dto.CreateMovieRequest;
import com.digitalmedia.movies.model.dto.MovieDto;
import com.digitalmedia.movies.model.dto.UpdateMovieRequest;
import com.digitalmedia.movies.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;


@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @GetMapping
    //@PreAuthorize("hasAnyRole('ROLE_user', 'ROLE_admin') AND hasAuthority('SCOPE_digital') AND hasAnyAuthority('GROUP_/Empleados/Departamento RH', 'GROUP_/Usuarios')")
    @PreAuthorize("hasAnyRole('ROLE_user', 'ROLE_admin')" )
    public List<MovieDto> getMovies(@AuthenticationPrincipal Jwt jwt) {
        return movieService.getMovies().stream()
                .map(movieMapper::toMovieDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{imdbId}")
    //@PreAuthorize("hasAnyRole('ROLE_user', 'ROLE_admin') AND hasAuthority('SCOPE_digital') AND hasAnyAuthority('GROUP_/Empleados/Departamento RH', 'GROUP_/Usuarios')")
    @PreAuthorize("hasRole('ROLE_user')")
    public MovieDto getMovie(@PathVariable String imdbId) {
        Movie movie = movieService.validateAndGetMovie(imdbId);
        return movieMapper.toMovieDto(movie);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    //@PreAuthorize("hasRole('ROLE_admin') AND hasAuthority('SCOPE_digital') AND hasAuthority('SCOPE_edicion') AND hasAuthority('GROUP_/Empleados')")
    @PreAuthorize("hasRole('ROLE_admin')")
    public MovieDto createMovie(@Valid @RequestBody CreateMovieRequest createMovieRequest, @AuthenticationPrincipal Jwt jwt) {
        Movie movie = movieMapper.toMovie(createMovieRequest);
        movie = movieService.saveMovie(movie);
        return movieMapper.toMovieDto(movie);
    }


    @PutMapping("/{imdbId}")
    //@PreAuthorize("hasRole('ROLE_admin') AND hasAuthority('SCOPE_digital') AND hasAuthority('SCOPE_edicion') AND hasAuthority('GROUP_/Empleados')")
    @PreAuthorize("hasRole('ROLE_admin')")
    public MovieDto updateMovie(@PathVariable String imdbId, @Valid @RequestBody UpdateMovieRequest updateMovieRequest) {
        Movie movie = movieService.validateAndGetMovie(imdbId);
        movieMapper.updateMovieFromDto(updateMovieRequest, movie);
        movie = movieService.saveMovie(movie);
        return movieMapper.toMovieDto(movie);
    }


    @DeleteMapping("/{imdbId}")
    //@PreAuthorize("hasRole('ROLE_admin') AND hasAuthority('SCOPE_digital') AND hasAuthority('SCOPE_edicion') AND hasAuthority('GROUP_/Empleados')")
    @PreAuthorize("hasRole('ROLE_admin')")
    public MovieDto deleteMovie(@PathVariable String imdbId) {
        Movie movie = movieService.validateAndGetMovie(imdbId);
        movieService.deleteMovie(movie);
        return movieMapper.toMovieDto(movie);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{imdbId}/comments")
    //@PreAuthorize("hasRole('ROLE_user') AND hasAuthority('SCOPE_digital') AND hasAuthority('GROUP_/Usuarios')")
    @PreAuthorize("hasRole('ROLE_user')")
    public MovieDto addMovieComment(@PathVariable String imdbId, @Valid @RequestBody AddCommentRequest addCommentRequest, Principal principal) {
        Movie movie = movieService.validateAndGetMovie(imdbId);
        Movie.Comment comment = new Movie.Comment(principal.getName(), addCommentRequest.getText(), LocalDateTime.now());
        movie.getComments().add(0, comment);
        movie = movieService.saveMovie(movie);
        return movieMapper.toMovieDto(movie);
    }
}