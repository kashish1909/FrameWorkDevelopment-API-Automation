package api.endpoints;


/*

Swagger URI: https://petstore.swagger.io

Create User (POST): https://petstore.swagger.io/v2/user
Get User (GET): https://petstore.swagger.io/v2/user/{username}
Update User (PUT): https://petstore.swagger.io/v2/user/{username}
Delete User (DELETE): https://petstore.swagger.io/v2/user/{username}
 */
public class Route {

    public static final String base_URL="https://petstore.swagger.io/v2";

    public static String post_URL=String.format("%s/user",base_URL);
    public static String gud_URL=String.format("%s/user/{username}",base_URL);

}
