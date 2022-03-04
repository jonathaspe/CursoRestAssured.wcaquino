package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.request;

public class OlaMundoTest {

    @Test
    public void testOlaMundo(){
        Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
        System.out.println(response.getBody().asString().equals("Ola Mundo!"));
        System.out.println(response.statusCode() == 200);
        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    public void devoConhecerOutrasFormasRestAssured(){
        get("https://restapi.wcaquino.me/ola").then().statusCode(200);

        given()
                //Pré-condições
        .when()
                //Ação de fato
           .get("https://restapi.wcaquino.me/ola")
        .then()
                //Verificações/assertivas
           .statusCode(200);
    }

    @Test
    public void devoConhecerMatcherHamcrest(){
        assertThat("Maria", is("Maria"));
        assertThat(128, is(128));
        assertThat("Maria", isA(String.class));
        assertThat(128, isA(Integer.class));
        assertThat(128d, isA(Double.class));
        assertThat(128d, greaterThan(120d));
        assertThat(128d, lessThan(130d));

        List<Integer> impares = Arrays.asList(1,3,5,7,9);
        assertThat(impares, hasSize(5));
        assertThat(impares, contains(1,3,5,7,9));
        assertThat(impares, containsInAnyOrder(9,3,7,5,1));
        assertThat(impares, hasSize(5));
        assertThat(impares, hasItem(1));

        assertThat("Maria", is(not("João")));
        assertThat("Maria", anyOf(is("Joaquina"), is("Maria")));
        assertThat("Joaquina", allOf(startsWith("Joa"), endsWith("ina"), containsString("qui")));
    }

    @Test
    public void devoValidarBody(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/ola")
                .then()
                .statusCode(200)
                .body(is("Ola Mundo!"))
                .body(containsString("Mundo"))
                .body(is(not(nullValue())));
    }
}