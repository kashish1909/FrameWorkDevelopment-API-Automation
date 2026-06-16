package api.test;

import api.endpoints.UserCRUD;
import api.payload.GetResponseUser;
import api.payload.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class  UserTest {

    Faker faker;
    User userData;
    UserCRUD users=new UserCRUD();
    ObjectMapper mapper=new ObjectMapper();
    public Logger logger;


    @BeforeClass
    public void setUp(){
        faker=new Faker();
        userData=User.builder()
                .id(faker.idNumber().hashCode())
                .username(faker.name().username())
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .email(faker.internet().safeEmailAddress())
                .password(faker.internet().password())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .userStatus(0).build();
        logger= LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void postUser() throws JsonProcessingException {
        Response res= users.createUser(userData);
        Assert.assertEquals(res.getStatusCode(),200);
        res.then().log().all();
    }

    @Test(priority = 2, dependsOnMethods = {"postUser"})
    public void getUser() throws JsonProcessingException {
        Response getNewCreatedUser=users.getUser(this.userData.getUsername());

//        String username=getNewCreatedUser.jsonPath().get("username").toString();
        String username=getNewCreatedUser.path("username");
        Assert.assertEquals(username,userData.getUsername());

        GetResponseUser getPOJO=mapper.readValue(getNewCreatedUser.asString(), GetResponseUser.class);
        getNewCreatedUser.then().log().body();
        Assert.assertEquals(getNewCreatedUser.getStatusCode(),200);
        Assert.assertEquals(getPOJO.getUsername(),userData.getUsername());
        Assert.assertEquals(getPOJO.getId(),userData.getId());
        Assert.assertEquals(getPOJO.getEmail(),userData.getEmail());
        Assert.assertEquals(getPOJO.getPassword(),userData.getPassword());

    }
//    9825141185-neelam merchant

    @Test(priority = 3)
    public void updateUser() throws JsonProcessingException {
        userData.setEmail(faker.internet().safeEmailAddress());
        userData.setPassword(faker.internet().password());
        userData.setUsername(faker.name().username());

        Response postRes=users.updateUser(this.userData.getUsername(),userData);
        Assert.assertEquals(postRes.getStatusCode(),200);
        getUser();
    }

    @Test(priority = 4)
    public void deleteUser(){
        Response deleRes=users.deleteUser(this.userData.getUsername());
        Assert.assertEquals(deleRes.getStatusCode(),200);
    }
}
