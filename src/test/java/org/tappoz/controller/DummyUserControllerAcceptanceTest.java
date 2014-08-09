package org.tappoz.controller;

import com.github.restdriver.serverdriver.Matchers;
import com.github.restdriver.serverdriver.RestServerDriver;
import com.github.restdriver.serverdriver.http.Url;
import com.github.restdriver.serverdriver.http.response.Response;
import com.google.common.net.HttpHeaders;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.MediaType;

public class DummyUserControllerAcceptanceTest {

    private static final String BASE_URL = "localhost:8080/SpringMvcSample/dummyUser";

    // https://github.com/rest-driver/rest-driver/wiki/Server-Driver#simple-gets
    @Test
    public void test_httpResponseTrue() {

        Url urlInspection = new Url(BASE_URL).withPath("/getRandomUser");

        Response response = RestServerDriver.get(urlInspection, RestServerDriver.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE));

        // http header checks
        MatcherAssert.assertThat(response, Matchers.hasStatusCode(200));
        MatcherAssert.assertThat(response, Matchers.hasHeaderWithValue(
                HttpHeaders.CONTENT_TYPE,
                org.hamcrest.Matchers.equalTo(MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")));

        // http message body checks
        MatcherAssert.assertThat(
                response.asJson(),
                // we are using json-path here: http://code.google.com/p/json-path/
                Matchers.hasJsonPath("$.userName.userName"));
    }
}
