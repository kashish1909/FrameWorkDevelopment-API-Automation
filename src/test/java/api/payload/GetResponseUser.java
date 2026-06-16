package api.payload;

public class GetResponseUser {
    private int id;
    private String username;
    private String email;
    private String password;
    private int userStatus;



    public int getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public int getUserStatus(){
        return this.userStatus;
    }
}
