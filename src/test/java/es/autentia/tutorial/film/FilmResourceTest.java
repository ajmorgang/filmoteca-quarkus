package es.autentia.tutorial.film;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
public class FilmResourceTest {
    @Test
    public void testList() {
        given()
                .when().get("/films")
                .then()
                .statusCode(200)
                .body("$.size()", is(2),
                        "year", containsInAnyOrder(1961, 1963),
                        "title", containsInAnyOrder("El verdugo", "Viridiana"));
    }

    @Test
    public void testAdd() {
        given()
                .body("{\"year\": 1982, \"title\": \"Terminator\", \"director\": \"Cameron\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/films")
                .then()
                .statusCode(200)
                .body("$.size()", is(3),
                        "year", containsInAnyOrder(1961, 1963, 1982),
                        "title", containsInAnyOrder("El verdugo", "Viridiana", "Terminator"));

        given()
                .body("{\"year\": 1982, \"title\": \"Terminator\", \"director\": \"Cameron\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .delete("/films")
                .then()
                .statusCode(200);
    }
}