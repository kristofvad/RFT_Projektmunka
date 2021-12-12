import org.junit.Test;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.request;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasKey;

public class TestTeacher {

    private final String BASE_URL = "http://localhost:8091";

    public JSONObject readJSON(String fileName) {
        Object obj = new Object();
        try {
            obj = new JSONParser().parse(new FileReader(System.getProperty("user.dir") + "/src/test/resources/" + fileName));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return (JSONObject) obj;
    }

    public String getUserId(String userName){
        return String.valueOf(get(BASE_URL + "/teacher/teachers").path("find {it.teacherName == '" + userName + "'}.id"));
    }

    public void deleteTeacher(String teacherName) {
        String teacherId = getUserId(teacherName);
        Response response = request("delete", BASE_URL + "/teacher/delete/" + teacherId);

        response.then()
                .statusCode(200);
    }

    @Test
    public void UserListStatus() {
        Response response = request("get", BASE_URL + "/teacher/teachers");

        response.then()
                .statusCode(200);
    }

    @Test
    public void TeachersListNotEmpty() {
        Response response = request("get", BASE_URL + "/teacher/teachers");

        response.then()
                .body("size()", is(not(empty())));
    }

    @Test
    public void InvalidTeacherErrorResponse() {
        Response response = request("get", BASE_URL + "/teacher/notValid");

        response.then()
                .statusCode(400);
    }

    @Test
    public void NonExistingTeacherErrorResponse() {
        Response response = request("get", BASE_URL + "/teacher/500");

        response.then()
                .statusCode(404);
    }


    @Test
    public void NewUserWithAlreadyExistingEmailErrorResponse() {
        RequestSpecification request = given().log().ifValidationFails();
        JSONObject testUserJson = readJSON("testTeacher.json");

        request.header("Content-Type", "application/json");
        request.body(JSONObject.toJSONString(testUserJson));

        Response response = request.request("post", BASE_URL + "/teacher/add");

        response.then()
                .statusCode(400);
    }

    @Test
    public void UsersListIsRequestedExpectProperFields() {
        Response response = request("get", BASE_URL + "/teacher/teachers");

        response.then()
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("userName"));
    }

}
