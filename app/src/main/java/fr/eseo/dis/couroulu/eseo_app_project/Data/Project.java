package fr.eseo.dis.couroulu.eseo_app_project.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by florent on 10/01/18.
 */
@SuppressWarnings("serial")
public class Project implements Serializable{

    private int idProject;
    private String title;
    private String descrip;
    private boolean poster;
    private Supervisor supervisor;
    private int confid;
    private List<Student> students;

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public boolean isPoster() {
        return poster;
    }

    public void setPoster(boolean poster) {
        this.poster = poster;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public int getConfid() {
        return confid;
    }

    public void setConfid(int confid) {
        this.confid = confid;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String toStringListStudent(){
        String res="Member(s) : \r\n";
        if(this.students != null) {
            if (this.students.size() != 0) {
                res = this.students.get(0).toStringMembers();
                if (this.students.size() != 1) {
                    for (int i = 1; i < this.students.size(); i++) {
                        res += "\r\n" + this.students.get(i).toStringMembers();
                    }
                }

            }
        }
        return res;
    }
}