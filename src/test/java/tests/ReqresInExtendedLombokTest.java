package tests;


import models.lombok.*;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import static specs.CreateUserSpec.createUserRequestSpec;
import static specs.CreateUserSpec.createUserResponseSpec;
import static specs.DeleteUserSpec.DeleteUserRequestSpec;
import static specs.DeleteUserSpec.DeleteUserResponseSpec;
import static specs.RegisterUserSpec.RegisterUserRequestSpec;
import static specs.RegisterUserSpec.RegisterUserResponseSpec;
import static specs.UpdateUserSpec.UpdateUserRequestSpec;
import static specs.UpdateUserSpec.UpdateUserResponseSpec;


public class ReqresInExtendedLombokTest extends TestBase {


    @Test
    void checkCreateNewUser() {

        step("Prepare testdata");
        RequestCreateUserBodyLombokModel BodyCreate = new RequestCreateUserBodyLombokModel();
        BodyCreate.setName("morpheus");
        BodyCreate.setJob("leader");


        ResponseCreateUserBodyLombokModel response = step("Make request", () ->
                given(createUserRequestSpec)
                        .body(BodyCreate)
                        .when()
                        .post()
                        .then()
                        .spec(createUserResponseSpec)
                        .extract().as(ResponseCreateUserBodyLombokModel.class));

        //  assertEquals("id",response.getId()); Проверка через Junit
        step("Verify response", () ->
                assertThat(response.getName()).isEqualTo("morpheus")); //Проверка через библиотеку assertj
    }


    @Test
    void checkUpdateUser() {

        step("Prepare testdata");
        RequestUpdateUserBodyLombokModel BodyUpdate = new RequestUpdateUserBodyLombokModel();
        BodyUpdate.setName("morpheus");
        BodyUpdate.setJob("zion resident");

        ResponseUpdateUserBodyLombokModel response = step("Make request", () ->
        given(UpdateUserRequestSpec)
                .body(BodyUpdate)
                .when()
                .put()
                .then()
                .spec(UpdateUserResponseSpec)
                .extract().as(ResponseUpdateUserBodyLombokModel.class));

        step("Verify response", () -> {
                    assertThat(response.getName()).isEqualTo("morpheus");
                    assertThat(response.getJob()).isEqualTo("zion resident");
                }
                );
    }

    @Test
    void checkDeleteUser() {


        given(DeleteUserRequestSpec)
                .log().uri()
                .when()
                .delete()
                .then()
                .spec(DeleteUserResponseSpec);

    }

    @Test
    void checkRegisterUser() {

        step("Prepare testdata");
        RequestRegisterUserBodyLombokModel BodyRegisterUser = new RequestRegisterUserBodyLombokModel();
        BodyRegisterUser.setEmail("eve.holt@reqres.in");
        BodyRegisterUser.setPassword("pistol");

        ResponseRegisterUserBodyLombokModel response = step("Make request", () ->
        given(RegisterUserRequestSpec)
                .body(BodyRegisterUser)
                .when()
                .post()
                .then()
                .spec(RegisterUserResponseSpec)
                .extract().as(ResponseRegisterUserBodyLombokModel.class));

        step("Verify response", () ->
                assertThat(response.getToken()).isNotNull());
    }
}


