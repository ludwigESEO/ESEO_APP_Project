package fr.eseo.dis.couroulu.eseo_app_project.Data;

import java.util.List;

/**
 * Created by florent on 11/01/18.
 */

public class Jury {

    private int idJury;
    private String date;
    private List<Student> members;
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

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String toStringListMembers(){
        String res="";
        if(members != null) {
            if (this.members.size() != 0) {
                res = this.members.get(0).toStringMembers();
                if (this.members.size() != 1) {
                    for (int i = 1; i < this.members.size(); i++) {
                        res += "\r\n " + this.members.get(i).toStringMembers();
                    }
                }

            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Jury{" +
                "idJury=" + idJury +
                ", date='" + date + '\'' +
                ", members=" + members +
                ", projects=" + projects +
                '}';
    }
}
