package specs;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.assertj.core.api.Assert;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.List;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;


public class WithGivenTotalSpec {
    public static RequestSpecification WithGivenTotalRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .baseUri("https://reqres.in")
            .basePath("api/users?page=2")
            .contentType(JSON);

    public static ResponseSpecification WithGivenTotalResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("total", Matchers.equalTo(12))
            .build();

}

