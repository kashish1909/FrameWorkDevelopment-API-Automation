//package api.test;
//
//import api.endpoints.UserCRUD;
//import api.payload.GetResponseUser;
//import api.payload.User;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.javafaker.Faker;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import api.utilities.DataProvider;
//import org.testng.annotations.Test;
//
//public class DDTest {
//
//    User newUser;
//    UserCRUD user;
//    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProvider.class)
//    public void postUserUsingExcelData(String fname,String lname,String email,String pass,String phoneNumber){
//        Faker faker=new Faker();
//        newUser= User.builder()
//                .id(faker.idNumber().hashCode())
//                .firstname(fname)
//                .lastname(lname)
//                .email(email)
//                .password(pass)
//                .phoneNumber(phoneNumber)
//                .userStatus(0).build();
//        Response res=user.createUser(newUser);
//        Assert.assertEquals(res.getStatusCode(),200);
//        res.then().log().body();
//    }
//
//    @Test(priority = 3,dataProvider = "Username",dataProviderClass = DataProvider.class)
//    public void getUser(String username) throws JsonProcessingException {
//        Response getUser=user.getUser(username);
//        Assert.assertEquals(getUser.getStatusCode(),200);
//        getUser.then().log().body();
//        ObjectMapper mapper=new ObjectMapper();
//        GetResponseUser getPojo=mapper.readValue(getUser.asString(), GetResponseUser.class);
//        System.out.println(getPojo);
//        Assert.assertEquals(getPojo.getEmail(),this.newUser.getEmail());
//    }
//
//    @Test(priority = 3,dataProvider = "Username",dataProviderClass = DataProvider.class)
//    public void deleteUser(String username){
//        Response response=user.deleteUser(username);
//        Assert.assertEquals(response.getStatusCode(),200);
//    }
//}
