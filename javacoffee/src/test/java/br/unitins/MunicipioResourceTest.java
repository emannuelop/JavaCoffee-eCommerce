package br.unitins;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import br.unitins.ecommerce.dto.municipio.MunicipioDTO;
import br.unitins.ecommerce.dto.municipio.MunicipioResponseDTO;
import br.unitins.ecommerce.resource.MunicipioResource;
import br.unitins.ecommerce.service.muncipio.MunicipioService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.inject.Inject;

@QuarkusTest
public class MunicipioResourceTest {

    @Inject
    MunicipioService municipioService;

    @Test
    public void getAllTest() {
        given()
                .when().get("/muncipios")
                .then()
                .statusCode(200);
    }

    @Test
    @TestHTTPEndpoint(MunicipioResource.class)
    public void insertTest() {
        MunicipioDTO municipio = new MunicipioDTO(
                "Miracema do Tocantins", 
                (long) 4);

        given()
                .contentType(ContentType.JSON)
                .body(municipio)
                .when().post()
                .then()
                .statusCode(201)
                .body("id", notNullValue(), 
                        "nome", is("Miracema do Tocantins"),
                        "siglaEstado", is("TO"), "nomeEstado", is("Tocantins"));
    }
    

}