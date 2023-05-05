package tests;

import models.pojo.CreateUserBodyModel;
import models.pojo.ResponseCreateUserBodyModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ReqresInExtendedTest {


    @Test
    void checkCreateNewUser() {
        //String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        CreateUserBodyModel BodyCreate = new CreateUserBodyModel();
        BodyCreate.setName("morpheus");
        BodyCreate.setJob("leader");

        ResponseCreateUserBodyModel response = given()
                .log().uri()
                .body(BodyCreate)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(ResponseCreateUserBodyModel.class);

        //  assertEquals("id",response.getId()); Проверка через Junit

        assertThat(response.getName()).isEqualTo("morpheus"); //Проверка через библиотеку assertj


    }
}