package br.ce.wcaquino.rest.tests.refat.suite;

import br.ce.wcaquino.rest.core.BaseTest;
import br.ce.wcaquino.rest.tests.refat.AuthTest;
import br.ce.wcaquino.rest.tests.refat.ContasTest;
import br.ce.wcaquino.rest.tests.refat.MovimentacaoTest;
import br.ce.wcaquino.rest.tests.refat.SaldoTest;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
        ContasTest.class,
        MovimentacaoTest.class,
        SaldoTest.class,
        AuthTest.class
})
public class Suite extends BaseTest {
    @BeforeClass
    public static void login(){
        Map<String, String> login = new HashMap<>();
        login.put("email", "vanessa@teste");
        login.put("senha", "123456");

        String TOKEN = given()
                .body(login)
                .when()
                .post("/signin")
                .then()
                .statusCode(200)
                .extract().path("token")
                ;

        requestSpecification.header("Authorization", "JWT " + TOKEN);

        RestAssured.get("/reset").then().statusCode(200);
    }
}
