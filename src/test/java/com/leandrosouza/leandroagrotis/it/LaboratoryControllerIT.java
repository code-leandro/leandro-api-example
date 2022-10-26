package com.leandrosouza.leandroagrotis.it;

import com.leandrosouza.leandroagrotis.api.payload.response.LaboratoryResponse;
import com.leandrosouza.leandroagrotis.api.payload.response.ListLaboratoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class LaboratoryControllerIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void findAll() throws Exception {
        ResponseEntity<ListLaboratoryResponse> response = testRestTemplate.exchange("/v1/laboratory", HttpMethod.GET, null,
                new ParameterizedTypeReference<ListLaboratoryResponse>() {
                });

        ListLaboratoryResponse listLaboratoryResponse = response.getBody();
        List<LaboratoryResponse> list = listLaboratoryResponse.getLaboratories();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listLaboratoryResponse).isNotNull();
        assertThat(list).isNotEmpty();
        assertThat(list).hasSize(1);
    }
}
