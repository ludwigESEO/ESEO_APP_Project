package fr.eseo.dis.couroulu.eseo_app_project.Data;

import java.io.Serializable;

/**
 * Created by Loulou on 10/01/2018.
 */
@SuppressWarnings("serial")
public class Supervisor implements Serializable{

    private String forename;
    private String surname;

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

    public String toStringSupervisor(){
        return "Supervisor : " + this.forename + " " + this.surname;
    }
}
