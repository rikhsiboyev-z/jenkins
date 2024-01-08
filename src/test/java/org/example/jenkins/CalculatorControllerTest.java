package org.example.jenkins;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CalculatorControllerTest {

    @Test
    void add() throws IOException, InterruptedException {
        int a = 10;
        int b = 20;

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest

                .newBuilder(URI.create("http://localhost:8081/add?a=%d&b=%d".formatted(a, b)))
                .GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();
        int result = Integer.parseInt(body);

        Assertions.assertEquals(a + b, result);

    }

}