package Reqres.stepDefinitions;

import Reqres.utilities.ExcelUtil;
import Reqres.utilities.ReqresAPI;
import Reqres.utilities.pojo.User;
import Reqres.utilities.pojo.registeredUser;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Reqres.utilities.ReqresAPI.*;
import static org.junit.Assert.*;

public class reqresStepDefinitions {
    ExcelUtil createdUserData = new ExcelUtil("src/test/resources/createdUserData.xlsx", "Sheet1");
    public static List<registeredUser> userList = new ArrayList<>();
    static User user = new User();

    //---------------------- GET USER LIST ---------------------------
    @When("I send GET request to endpoint with the following page number {int}")
    public void iSendGETRequestToEndpointWithTheFollowingPageNumber(int pageNumber) {
        getUsersList(pageNumber);
    }

    @Then("I get user list")
    public void iGetUserList() {
        userList.addAll(response.jsonPath().getList("data"));

        //assertEquals(6, userList.size());
    }

    //---------------------- GET SINGLE USER DATA ---------------------------
    @When("I send GET request to endpoint with the following id number: {int}")
    public void iSendGETRequestToEndpointWithTheFollowingIdNumber(int id) {
        getSingleUser(id);
    }

    @Then("I get the registered user informations")
    public void iGetTheRegisteredUserInformations(DataTable table) {
        List<Map<String, String>> maps = table.asMaps();
        String id = maps.get(0).get("id");
        String email = maps.get(0).get("email");
        String first_name = maps.get(0).get("first_name");
        String last_name = maps.get(0).get("last_name");

        registeredUser user = response.jsonPath().getObject("data", registeredUser.class);

        if(response.statusCode() == 200){
            assertEquals(id, user.getId() +"");
            assertEquals(email, user.getEmail());
            assertEquals(first_name, user.getFirst_name());
            assertEquals(last_name, user.getLast_name());
        }
    }

    @When("I send GET request to endpoint with created user id")
    public void iSendGETRequestToEndpointWithCreatedUserId() {
        getSingleUser(userId);
    }

    @Then("I get the created user informations")
    public void iGetTheCreatedUserInformations() {
        assertNotNull(response.jsonPath().getString("data"));
    }

    //---------------------- POST REGISTER USER ---------------------------
    @When("I send POST request to endpoint with given user informations")
    public void iSendPOSTRequestToEndpointWithGivenUserInformations() {
            registerUser();
        }

    @When("I send POST request to endpoint with RANDOM user informations")
    public void iSendPOSTRequestToEndpointWithRANDOMUserInformations() {
        registerUser("randomEmail@info.com");
    }
    //---------------------- CHECK BODY IF IT IS EMPTY ---------------------------

    @And("verify data does NOT have any error")
    public void iGetTheFollowingDataIdAndToken() {
        if(response.statusCode() == 200){
            assertFalse(response.body().toString().contains("error"));
        }
    }
    //---------------------- CREATE A NEW USER ---------------------------

    @When("I send POST request to endpoint with generated name and job")
    public void iSendPOSTRequestToEndpointWithGeneratedNameAndJob() {
        createUser();

        user = response.jsonPath().getObject("", User.class);

        System.out.println("user created = " + user);
    }
    @When("I send POST request to endpoint with INVALID {string} and {string}")
    public void iSendPOSTRequestToEndpointWithINVALIDNameAndJob(String name, String job) {
        createUser("/api/users", name, job);
        //status_code_should_be(400);
    }

    //---------------------- UPDATE USER ---------------------------
    @When("I send PUT request to endpoint with created user id")
    public void iSendPUTRequestToEndpointWithCreatedUserId() {
        updateUser_PUT(userId, generateUser().getName(), generateUser().getJob());
    }

    //---------------------- UPDATE USER WITH MULTIPLE DATA ---------------------------

    @When("I send POST request to endpoint with following name and job")
    public void i_send_POST_request_to_endpoint_with_following_and(DataTable table) {
        Map<String, String> userData = table.asMaps().get(0);

        User user = new User();
        user.setName(userData.get("name"));
        user.setJob(userData.get("job"));

        createUser("/api/users", user.getName(), user.getJob());

        System.out.println("user ID: " + userId);
    }

    //---------------------- VERIFY STATUS CODE ---------------------------
    @Then("status code should be {int}")
    public void status_code_should_be(int statusCode) {
        assertEquals(statusCode, response.statusCode());
    }

    //---------------------- GET SINGLE USER DATA ---------------------------

    @And("I get user informations")
    public void iGetUserInformations() {
        User userUpdated = response.jsonPath().getObject("", User.class);

        user.setName(userUpdated.getName() == null? user.getName():userUpdated.getName());
        user.setJob(userUpdated.getJob() == null? user.getJob():userUpdated.getJob());
        user.setUpdatedAt(userUpdated.getUpdatedAt());

        System.out.println("User updated: " + user);
    }

    //---------------------- DELETE USER DATA ---------------------------

    @When("I send DELETE request to endpoint with created user id")
    public void iSendDELETERequestToEndpointWithCreatedUserNameId() {
        deleteUser(userId);
    }
}
