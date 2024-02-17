package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlsRepository;
import io.javalin.Javalin;
import kong.unirest.HttpResponse;
import kong.unirest.HttpStatus;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public final class AppTest {
    private static final int PORT = 0;
    private static Javalin app;
    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() throws SQLException, IOException {
        app = App.getApp();
        app.start(PORT);

        baseUrl = "http://localhost:" + app.port();
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }

    @Test
    void indexTest() {
        HttpResponse<String> response = Unirest.get(baseUrl).asString();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void urlIndexTest() {
        HttpResponse<String> response = Unirest.get(baseUrl + "/urls").asString();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testAdding() throws SQLException {
        String url = "https://www.facebook.com:443/login";
        String expected = "https://www.facebook.com:443";

        Unirest.post(baseUrl + "/urls").field("url", url).asString();
        Optional<Url> actual = UrlsRepository.findByName(expected);

        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual.get().getName()).isEqualTo(expected);

        HttpResponse<String> response = Unirest.get(baseUrl + "/urls").asString();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains(expected);
    }

    @Test
    void testShowing() {

    }
}
