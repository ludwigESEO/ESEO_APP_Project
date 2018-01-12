package fr.eseo.dis.couroulu.eseo_app_project.Data;

import java.util.List;

/**
 * Created by florent on 11/01/18.
 */

public class Jury {

    private int idJury;
    private String date;
    private List<Project> projects;

    public int getIdJury() {
        return idJury;
    }

    public void setIdJury(int idJury) {
        this.idJury = idJury;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
