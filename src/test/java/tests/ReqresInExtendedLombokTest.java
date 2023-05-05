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
import static specs.DeleteUserSpec.DeleteUserRequestSpec;
import static specs.DeleteUserSpec.DeleteUserResponseSpec;
import static specs.RegisterUserSpec.RegisterUserRequestSpec;
import static specs.RegisterUserSpec.RegisterUserResponseSpec;
import static specs.UpdateUserSpec.UpdateUserRequestSpec;
import static specs.UpdateUserSpec.UpdateUserResponseSpec;
import static specs.WithGivenTotalSpec.WithGivenTotalRequestSpec;
import static specs.WithGivenTotalSpec.WithGivenTotalResponseSpec;


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

        step("Verify response", () ->
                assertThat(response.getName()).isEqualTo("morpheus"));
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

    @Test
    void checkDeleteUser() {


        step("Make request", () -> given(DeleteUserRequestSpec)
                .log().uri()
                .when()
                .delete()
                .then()
                .spec(DeleteUserResponseSpec));

    }

    @Test
    void checkWithGivenTotal() {

        ResponseWithGivenTotalModel response = step("Make request", () ->
                given(WithGivenTotalRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(WithGivenTotalResponseSpec)
                        .extract().as(ResponseWithGivenTotalModel.class));

        step("Verify emails, avatars and number of users", () -> assertAll(
                () -> assertTrue(response.getData().stream().allMatch(x -> x.getEmail().endsWith("reqres.in"))),
                () -> assertTrue(response.getData().stream().allMatch(x -> x.getAvatar().startsWith("https://reqres.in/img/faces/")))));

    }
}


