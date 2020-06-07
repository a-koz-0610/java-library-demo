package org.wecancodeit.library.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wecancodeit.library.models.Campus;
import org.wecancodeit.library.storage.CampusStorage;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private CampusStorage campusStorage;
    private Campus testCampus;

    @BeforeEach
    public void testClassSetup() {
        testCampus = new Campus("HTTP Request Test Campus");
        campusStorage.store(testCampus);
    }

    @Test
    public void campusesEndPointReturnsOK() {
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/campuses", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void specificEndPointReturnsOK() {
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/campuses/" + testCampus.getLocation(), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
