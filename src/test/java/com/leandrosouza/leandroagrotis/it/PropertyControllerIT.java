package com.leandrosouza.leandroagrotis.it;

import com.leandrosouza.leandroagrotis.api.payload.response.PropertyResponse;
import com.leandrosouza.leandroagrotis.api.payload.response.ListPropertyResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class PropertyControllerIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void findAll() throws Exception {
        ResponseEntity<ListPropertyResponse> response = testRestTemplate.exchange("/v1/property", HttpMethod.GET, null,
                new ParameterizedTypeReference<ListPropertyResponse>() {
                });

        ListPropertyResponse listPropertyResponse = response.getBody();
        List<PropertyResponse> list = listPropertyResponse.getProperties();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listPropertyResponse).isNotNull();
        assertThat(list).isNotEmpty();
        assertThat(list).hasSize(1);
    }
}
