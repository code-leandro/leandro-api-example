package com.leandrosouza.leandroagrotis.it;

import com.leandrosouza.leandroagrotis.api.payload.request.LaboratoryRequest;
import com.leandrosouza.leandroagrotis.api.payload.request.PersonRequest;
import com.leandrosouza.leandroagrotis.api.payload.request.PropertyRequest;
import com.leandrosouza.leandroagrotis.api.payload.response.ListPersonResponse;
import com.leandrosouza.leandroagrotis.api.payload.response.PersonResponse;
import com.leandrosouza.leandroagrotis.builder.FactoryLaboratoryTestUtil;
import com.leandrosouza.leandroagrotis.builder.FactoryPersonTestUtil;
import com.leandrosouza.leandroagrotis.builder.FactoryPropertyTestUtil;
import com.leandrosouza.leandroagrotis.domain.Person;
import com.leandrosouza.leandroagrotis.persistence.LaboratoryRepository;
import com.leandrosouza.leandroagrotis.persistence.PersonRepository;
import com.leandrosouza.leandroagrotis.persistence.PropertyRepository;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class PersonControllerIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    PersonRepository repository;

    @Test
    void findAll() throws Exception {

        Person person = FactoryPersonTestUtil.getPerson();
        person.setIdLaboratory(1);
        person.setIdProperty(1);
        repository.save(person);

        ListPersonResponse people = testRestTemplate.exchange("/v1/person", HttpMethod.GET, null,
                new ParameterizedTypeReference<ListPersonResponse>() {
                }).getBody();

        assertThat(people).isNotNull();
        assertThat(people.getPeople()).hasSize(1);
    }

    @Test
    void save() throws Exception {

        PersonRequest personRequest = FactoryPersonTestUtil.getPersonRequest();
        personRequest.setLaboratoryRequest(new LaboratoryRequest(1, FactoryLaboratoryTestUtil.NAME_TEST));
        personRequest.setPropertyRequest(new PropertyRequest(1, FactoryPropertyTestUtil.NAME_TEST));
        personRequest.setStart(LocalDateTime.now());
        personRequest.setEndTime(LocalDateTime.now());
        personRequest.setIdEmployer("XXX");

        ResponseEntity<PersonResponse> response = testRestTemplate.postForEntity("/v1/person", personRequest, PersonResponse.class);
        PersonResponse personResponse = response.getBody();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(personResponse.getName()).isEqualTo(FactoryPersonTestUtil.NAME);
    }

    @Test
    void findById() throws Exception {

        Person person = FactoryPersonTestUtil.getPerson();
        person.setIdLaboratory(1);
        person.setIdProperty(1);
        Person saved = repository.save(person);

        ResponseEntity<PersonResponse> response = testRestTemplate.exchange("/v1/person/{id}", HttpMethod.GET, null,
                new ParameterizedTypeReference<PersonResponse>() {
                }, saved.getId());

        PersonResponse personResponse = response.getBody();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(personResponse.getId()).isEqualTo(saved.getId());
        assertThat(personResponse.getName()).isEqualTo(FactoryPersonTestUtil.NAME);
    }

    @Test
    void findByIdNotFound() throws Exception {

        ResponseEntity<PersonResponse> response = testRestTemplate.exchange("/v1/person/{id}", HttpMethod.GET, null,
                new ParameterizedTypeReference<PersonResponse>() {
                }, 10);

        PersonResponse personResponse = response.getBody();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void delete() throws Exception {

        Person person = FactoryPersonTestUtil.getPerson();
        person.setIdLaboratory(1);
        person.setIdProperty(1);
        Person saved = repository.save(person);

        ResponseEntity<PersonResponse> response = testRestTemplate.exchange("/v1/person/{id}", HttpMethod.DELETE, null,
                new ParameterizedTypeReference<PersonResponse>() {
                }, saved.getId());

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteNotFound() throws Exception {

        ResponseEntity<PersonResponse> response = testRestTemplate.exchange("/v1/person/{id}", HttpMethod.DELETE, null,
                new ParameterizedTypeReference<PersonResponse>() {
                }, 10);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
