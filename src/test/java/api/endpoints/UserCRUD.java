package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.response.Response;

public class UserCRUD {

    public Response createUser(User newUser){
        Response postRes=given()
                .contentType("application/json")
                .accept("application/json")
                .body(newUser)
                .when()
                .post(Route.post_URL);

        return postRes;
    }

    public Response getUser(String username){
        Response getRes=given().pathParam("username",username).when().get(Route.gud_URL);
        return getRes;
    }

    public Response updateUser(String username,User updateUser){
        Response putRes=given()
                .contentType("application/json")
                .accept("application/json")
                .pathParam("username",username)
                .body(updateUser)
                .when()
                .put(Route.gud_URL);
        return putRes;
    }

    public Response deleteUser(String username){
        return given().pathParam("username",username).when().delete(Route.gud_URL);
    }
}
