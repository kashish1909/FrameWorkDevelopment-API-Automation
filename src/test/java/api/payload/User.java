package api.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
{
  "id": 1,
  "username": "abc@1234",
  "firstName": "suresh",
  "lastName": "darpan",
  "email": "suresh@example.com",
  "password": "Pass@1234",
  "phone": "9876543213",
  "userStatus": 0
}
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phoneNumber;
    private int userStatus;
}
