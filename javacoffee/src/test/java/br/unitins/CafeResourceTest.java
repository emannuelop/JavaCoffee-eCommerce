package br.unitins;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import br.unitins.ecommerce.dto.cafe.CafeDTO;
import br.unitins.ecommerce.dto.cafe.CafeResponseDTO;
import br.unitins.ecommerce.service.cafe.CafeService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class CafeResourceTest {

    @Inject
    CafeService cafeService;

    @Test
    public void getAllTest() {

        given()
                .when().get("/cafes")
                .then()
                .statusCode(200);
    }

    @Test
    public void getByIdTest() {

        CafeDTO cafe = new CafeDTO(
                "Cafe Black Tucano",
                "Muito Zica",
                2l,
                34.95,
                10,
                "Esquenta ele",
                "Tipo 1",
                1);

        Long id = cafeService.insert(cafe).id();

        given()
                .when().get("/cafes/" + id)
                .then()
                .statusCode(200);
    }

    @Test
    public void insertTest() {

        CafeDTO cafe = new CafeDTO(
                "Cafe Black Tucano",
                "Muito Zica",
                2l,
                34.95,
                10,
                "Esquenta ele",
                "Tipo 1",
                1);

        given()
                .contentType(ContentType.JSON)
                .body(cafe)
                .when().post("/cafes")
                .then()
                .statusCode(201)
                .body("id", notNullValue(), "nome", is("Cafe Black Tucano"),
                        "descricao", is("Muito Zica"),
                          "nomeMarca", is("Nestle"),
                           "preco", is(34.95f),
                            "estoque", is("Disponível"),
                             "modoPreparo", is("Esquenta ele"),
                              "tipo", is("Tipo 1"),
                               "intensidade.label", is("Suave"));
    }

    @Test
    public void updateTest() {

        CafeDTO cafe = new CafeDTO(
                "Cafe Black Tucano",
                "Muito Zica",
                2l,
                34.95,
                10,
                "Esquenta ele",
                "Tipo 1",
                1);

        Long id = cafeService.insert(cafe).id();

        CafeDTO cafeUpdate = new CafeDTO(
                "Cafe Fazenda Floresta",
                "Zica demais",
                1l,
                32.30,
                10,
                "joga água",
                "Tipo 2",
                2);

        given()
                .contentType(ContentType.JSON)
                .body(cafeUpdate)
                .when().put("/cafes/" + id)
                .then()
                .statusCode(204);

        CafeResponseDTO cafeResponse = cafeService.getById(id);

        assertThat(cafeResponse.nome(), is("Cafe Fazenda Floresta"));
        assertThat(cafeResponse.descricao(), is("Zica demais"));
        assertThat(cafeResponse.nomeMarca(), is("três corações"));
        assertThat(cafeResponse.preco(), is(32.30));
        assertThat(cafeResponse.estoque(), is("Disponível"));
        assertThat(cafeResponse.modoPreparo(), is("joga água"));
        assertThat(cafeResponse.tipo(), is("Tipo 2"));
        assertThat(cafeResponse.intensidade().getLabel(), is("Media"));

    }

    @Test
    public void deleteTest() {

        CafeDTO cafe = new CafeDTO(
                "Cafe Black Tucano",
                "Muito Zica",
                2l,
                34.95,
                10,
                "Esquenta ele",
                "Tipo 1",
                1);

        Long id = cafeService.insert(cafe).id();

        given()
                .when().delete("/cafes/" + id)
                .then()
                .statusCode(204);

        CafeResponseDTO cafeResponse = null;

        try {

            cafeResponse = cafeService.getById(id);
        } catch (Exception e) {

        } finally {
            assertNull(cafeResponse);
        }
    }

    @Test
    public void countTest() {

        given()
                .when().get("/cafes/count")
                .then()
                .statusCode(200);
    }

    @Test
    public void getByNomeTest() {

        CafeDTO cafe = new CafeDTO(
                "Cafe Black Tucano",
                "Muito Zica",
                2l,
                34.95,
                10,
                "Esquenta ele",
                "Tipo 1",
                1);

        String nome = cafeService.insert(cafe).nome();

        given()
                .when().get("/cafes/searchByNome/" + nome)
                .then()
                .statusCode(200);
    }

    @Test
    public void getByIntensidadeTest() {

        CafeDTO cafe = new CafeDTO(
                "Cafe Black Tucano",
                "Muito Zica",
                2l,
                34.95,
                10,
                "Esquenta ele",
                "Tipo 1",
                1);
        Integer intensidade = cafeService.insert(cafe).intensidade().getId();

        given()
                .when().get("/cafes/searchByIntensidade/" + intensidade)
                .then()
                .statusCode(200);
    }

    @Test
    public void getByMarcaTest() {

        CafeDTO cafe = new CafeDTO(
                "Cafe Black Tucano",
                "Muito Zica",
                2l,
                34.95,
                10,
                "Esquenta ele",
                "Tipo 1",
                1);

        String marca = cafeService.insert(cafe).nomeMarca();

        given()
                .when().get("/cafes/searchByMarca/" + marca)
                .then()
                .statusCode(200);
    }

    @Test
    public void filterByPrecoMinTest() {

        Double precoMinimo = 60.0;

        given()
                .when().get("/cafes/filterByPrecoMin/" + precoMinimo)
                .then()
                .statusCode(200);

    }

    @Test
    public void filterByPrecoMaxTest() {

        Double precoMaximo = 160.0;

        given()
                .when().get("/cafes/filterByPrecoMax/" + precoMaximo)
                .then()
                .statusCode(200);

    }

    @Test
    public void filterByEntrePrecoTest() {

        Double precoMaximo = 160.0;
        Double precoMinimo = 60.0;

        given()
                .when().get("/cafes/filterByEntrePreco/" + precoMaximo + "/" + precoMinimo)
                .then()
                .statusCode(200);

    }

}
