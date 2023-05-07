package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class WithGivenTotalSpec {
    public static RequestSpecification withGivenTotalRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .baseUri("https://reqres.in")
            .basePath("api/users?page=2")
            .contentType(JSON);

    public static ResponseSpecification withGivenTotalResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/listOfUsersSchema.json"))
            .build();

}

