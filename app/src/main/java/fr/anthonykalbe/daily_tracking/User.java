package fr.anthonykalbe.daily_tracking;

public class User {

    private String id;
    private String pwd;

    public User(String id, String pwd ){
        this.id = id;
        this.pwd = pwd;
    }

    public boolean checkExists(){
        return true;
    }

    public String getID(){
        return this.id;
    }
}
