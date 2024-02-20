package fr.anthonykalbe.daily_tracking;

import java.util.Map;

public class User {

    private String id;
    private String pwd;

    public User(String id, String pwd ){
        this.id = id;
        this.pwd = pwd;
    }

    public boolean checkExists(){
        //APIManager apiManager = new APIManager("login", Map.of("identifiant",  this.id, "password", this.pwd));
        return true;
    }

    public String getID(){
        return this.id;
    }
}
