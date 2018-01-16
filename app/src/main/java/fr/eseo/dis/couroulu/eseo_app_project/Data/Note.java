package fr.eseo.dis.couroulu.eseo_app_project.Data;

/**
 * Created by root on 16/01/18.
 */

public class Note {
    private int idUser;
    private String forename;
    private String surname;
    private String mynote;
    private String avgnote;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getMynote() {
        return mynote;
    }

    public void setMynote(String mynote) {
        this.mynote = mynote;
    }

    public String getAvgnote() {
        return avgnote;
    }

    public void setAvgnote(String avgnote) {
        this.avgnote = avgnote;
    }
}
