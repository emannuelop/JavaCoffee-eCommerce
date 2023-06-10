package br.unitins;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import br.unitins.ecommerce.dto.cafe.CafeDTO;
import br.unitins.ecommerce.dto.cafe.CafeResponseDTO;
import br.unitins.ecommerce.service.cafe.CafeService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
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

        given()
                .when().get("/cafes/" + 1)
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"Admin"})
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
    @TestSecurity(user = "testUser", roles = {"User"})
    public void insertForbiddenTest() {

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
                .statusCode(403);
    }

    @Test
    public void insertUnauthorizedTest() {

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
                .statusCode(401);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"Admin"})
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
    @TestSecurity(user = "testUser", roles = {"Admin"})
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
    @TestSecurity(user = "testUser", roles = {"Admin"})
    public void countTest() {

        given()
                .when().get("/cafes/count")
                .then()
                .statusCode(200);
    }

    @Test
    public void getByNomeTest() {

        given()
                .when().get("/cafes/searchByNome/" + "cafe")
                .then()
                .statusCode(200);
    }

    @Test
    public void getByIntensidadeTest() {

        given()
                .when().get("/cafes/searchByIntensidade/" + 1)
                .then()
                .statusCode(200);
    }

    @Test
    public void getByMarcaTest() {

        given()
                .when().get("/cafes/searchByMarca/" + "Nestle")
                .then()
                .statusCode(200);
    }

    @Test
    public void filterByPrecoMinTest() {

        given()
                .when().get("/cafes/filterByPrecoMin/" + 60.0)
                .then()
                .statusCode(200);

    }

    @Test
    public void filterByPrecoMaxTest() {

        given()
                .when().get("/cafes/filterByPrecoMax/" + 160.0)
                .then()
                .statusCode(200);

    }

    @Test
    public void filterByEntrePrecoTest() {

        given()
                .when().get("/cafes/filterByEntrePreco/" + 160.0 + "/" + 60.0)
                .then()
                .statusCode(200);

    }
}
