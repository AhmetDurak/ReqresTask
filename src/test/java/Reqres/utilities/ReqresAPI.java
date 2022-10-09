package Reqres.utilities;

import Reqres.utilities.pojo.User;
import Reqres.utilities.pojo.registeredUser;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;

import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class ReqresAPI {
    // Whenever a method is called, response will be updated
    // So it can be used for any specific data retrieval
    public static Response response;

    public static int userId;
    public static String userEmail;
    public static String password;
    public static String token;

    public static Response getUsersList(int pageNumber) {
        response = given()
                .accept(ContentType.JSON)
                .queryParam("page", pageNumber)
                .when()
                .get(Environment.BASE_URL + "/api/users");

        return response;
    }

    public static Response getSingleUser(int id) {
        response = given()
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get(Environment.BASE_URL + "/api/users/{id}");

        //userId = response.jsonPath().getInt("data.id");
        userEmail = response.jsonPath().getString("data.email");

        return response;
    }

    public static Response registerUser() {
        password = new Faker().internet().password(5, 10, true);

        registeredUser user = new registeredUser();
        user.setEmail(userEmail);
        user.setPassword(password);

        response = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(Environment.BASE_URL + "/api/register");

        token = response.jsonPath().getString("token");

        return response;
    }

    public static Response registerUser(String userEmail) {
        password = new Faker().internet().password(5, 10, true);

        registeredUser user = new registeredUser();
        user.setEmail(userEmail);
        user.setPassword(password);

        response = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(Environment.BASE_URL + "/api/register");

        token = response.jsonPath().getString("token");

        return response;
    }

    public static Response createUser() {
        response = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("Authorization", token)
                .body(generateUser())
                .when()
                .post(Environment.BASE_URL + "/api/users");
        userId = response.jsonPath().getInt("id");
        return response;
    }

    public static Response createUser(String endpoint, String name, String job) {
        response = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(generateUser(name, job))
                .log().body()
                .when()
                .post(Environment.BASE_URL + endpoint);
        userId = response.jsonPath().getInt("id");
        return response;
    }

    public static Response updateUser_PUT(int id) {
        response =  given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("Authorization", token)
                .body("")
                .and().pathParam("id", id)
                .when()
                .put(Environment.BASE_URL + "/api/users/{id}");

        return response;
    }

    public static Response updateUser_PUT(int id, String name, String job) {
        response =  given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("Authorization", token)
                .body(generateUser(name, job))
                .and().pathParam("id", id)
                .when()
                .put(Environment.BASE_URL + "/api/users/{id}");

        return response;
    }

    public static Response updateUserName(int id, String name) {
        HashMap<String, String> nameMap = new HashMap<>();
        nameMap.put("name", name);

        response = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("Authorization", token)
                .body(nameMap)
                .and().pathParam("id", id)
                .when()
                .put(Environment.BASE_URL + "/api/users/{id}");
        return response;
    }

    public static Response updateUserJob(int id, String userJob) {
        HashMap<String, String> job = new HashMap<>();
        job.put("job", userJob);
        response = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("Authorization", token)
                .body(job)
                .and().pathParam("id", id)
                .when()
                .put(Environment.BASE_URL + "/api/users/{id}");

        return response;
    }

    public static Response deleteUser(int id) {
        response = given()
                .header("Authorization", token)
                .and()
                .pathParam("id", id)
                .when()
                .delete(Environment.BASE_URL + "/api/users/{id}");

        return response;
    }

    public static Response logOut() {
        response = given()
                .header("Authorization", token)
                .when()
                .delete(Environment.BASE_URL + "/api/logout");

        return response;
    }

    public static User generateUser() {
        User user = new User();
        user.setName(new Faker().name().firstName());
        user.setJob(new Faker().job().title());

        return user;
    }

    private static User generateUser(String userName, String jobTitle) {
        User user = new User();
        user.setName(userName);
        user.setJob(jobTitle);

        return user;
    }

}
