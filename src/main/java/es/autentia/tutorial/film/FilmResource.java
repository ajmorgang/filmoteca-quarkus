package es.autentia.tutorial.film;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/films")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FilmResource {


    private Set<Film> films = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FilmResource() {
        films.add(new Film(1963, "El verdugo", "Luis García Berlanga"));
        films.add(new Film(1961, "Viridiana", "Luis Buñuel"));
    }

    @GET
    public Set<Film> list() {
        return films;
    }

    @POST
    public Set<Film> add(Film film) {
        films.add(film);
        return films;
    }

    @DELETE
    public Set<Film> delete(Film film) {
        films.removeIf(existingFilm -> existingFilm.title.contentEquals(film.title));
        return films;
    }
}