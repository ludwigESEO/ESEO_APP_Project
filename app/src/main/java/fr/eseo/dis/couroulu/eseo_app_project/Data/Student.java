package fr.eseo.dis.couroulu.eseo_app_project.Data;

/**
 * Created by florent on 10/01/18.
 */

public class Student {
    private int userId;
    private String forename;
    private String surname;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String toStringMembers() {
        return "forename : " + forename + ", surname : " + surname;
    }
}