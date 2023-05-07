package tests;


import models.lombok.*;
import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.CreateUserSpec.createUserRequestSpec;
import static specs.CreateUserSpec.createUserResponseSpec;
import static specs.DeleteUserSpec.*;
import static specs.RegisterUserSpec.*;
import static specs.UpdateUserSpec.*;
import static specs.WithGivenTotalSpec.*;


public class ReqresInExtendedLombokTest extends TestBase {


    @Test
    void checkCreateNewUser() {

        step("Prepare testdata");
        RequestCreateUserBodyLombokModel bodyCreate = new RequestCreateUserBodyLombokModel();
        bodyCreate.setName("morpheus");
        bodyCreate.setJob("leader");


        ResponseCreateUserBodyLombokModel response = step("Make request", () ->
                given(createUserRequestSpec)
                        .body(bodyCreate)
                        .when()
                        .post()
                        .then()
                        .spec(createUserResponseSpec)
                        .extract().as(ResponseCreateUserBodyLombokModel.class));

        step("Verify response", () ->
                assertThat(response.getName()).isEqualTo("morpheus"));
    }


    @Test
    void checkUpdateUser() {

        step("Prepare testdata");
        RequestUpdateUserBodyLombokModel bodyUpdate = new RequestUpdateUserBodyLombokModel();
        bodyUpdate.setName("morpheus");
        bodyUpdate.setJob("zion resident");

        ResponseUpdateUserBodyLombokModel response = step("Make request", () ->
                given(updateUserRequestSpec)
                        .body(bodyUpdate)
                        .when()
                        .put()
                        .then()
                        .spec(updateUserResponseSpec)
                        .extract().as(ResponseUpdateUserBodyLombokModel.class));

        step("Verify response", () -> {
                    assertThat(response.getName()).isEqualTo("morpheus");
                    assertThat(response.getJob()).isEqualTo("zion resident");
                }
        );
    }


    @Test
    void checkRegisterUser() {

        step("Prepare testdata");
        RequestRegisterUserBodyLombokModel bodyRegisterUser = new RequestRegisterUserBodyLombokModel();
        bodyRegisterUser.setEmail("eve.holt@reqres.in");
        bodyRegisterUser.setPassword("pistol");

        ResponseRegisterUserBodyLombokModel response = step("Make request", () ->
                given(registerUserRequestSpec)
                        .body(bodyRegisterUser)
                        .when()
                        .post()
                        .then()
                        .spec(registerUserResponseSpec)
                        .extract().as(ResponseRegisterUserBodyLombokModel.class));

        step("Verify response", () ->
                assertThat(response.getToken()).isNotNull());
    }

    @Test
    void checkDeleteUser() {


        step("Make request", () -> given(deleteUserRequestSpec)
                .log().uri()
                .when()
                .delete()
                .then()
                .spec(deleteUserResponseSpec));

    }

    @Test
    void checkWithGivenTotal() {

        ResponseWithGivenTotalModel response = step("Make request", () ->
                given(withGivenTotalRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(withGivenTotalResponseSpec)
                        .extract().as(ResponseWithGivenTotalModel.class));

        step("Verify emails, avatars and number of users", () -> assertAll(
                () -> assertTrue(response.getData().stream().allMatch(x -> x.getEmail().endsWith("reqres.in"))),
                () -> assertTrue(response.getData().stream().allMatch(x -> x.getAvatar().startsWith("https://reqres.in/img/faces/")))));

    }
}


