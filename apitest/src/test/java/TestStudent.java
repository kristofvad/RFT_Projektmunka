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

public class TestStudent {

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

    public String getStudentId(String studentName){
        return String.valueOf(get(BASE_URL + "/student/students").path("find {it.studentName == '" + studentName + "'}.id"));
    }

    public void deleteStudent(String studentName) {
        String studentId = getStudentId(studentName);
        Response response = request("delete", BASE_URL + "/student/delete/" + studentId);

        response.then()
                .statusCode(200);
    }



    @Test
    public void InvalidStudentRequestedExpectErrorResponse() {
        Response response = request("get", BASE_URL + "/student/notValid");

        response.then()
                .statusCode(400);
    }

    @Test
    public void NonExistingStudentRequestedExpectErrorResponse() {
        Response response = request("get", BASE_URL + "/student/500");

        response.then()
                .statusCode(200);
    }



}
