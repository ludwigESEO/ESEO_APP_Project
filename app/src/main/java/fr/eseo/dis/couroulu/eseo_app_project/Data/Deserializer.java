package fr.eseo.dis.couroulu.eseo_app_project.Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loulou on 10/01/2018.
 */

public class Deserializer {
   public static synchronized Project deserializeProject(JSONObject json) throws Exception {
        Project project = new Project();

        if (json.has("projectId"))
            project.setIdProject(json.getInt("projectId"));
        if (json.has("title"))
            project.setTitle(json.getString("title"));
        if (json.has("descrip"))
            project.setDescrip(json.getString("descrip"));
        if (json.has("poster"))
            project.setPoster(json.getBoolean("poster"));
        if (json.has("supervisor"))
            project.setSupervisor(deserializeSupervisor(json.getJSONObject("supervisor")));
        if (json.has("confid"))
            project.setConfid(json.getInt("confid"));
        if (json.has("students")){
            JSONArray studs = json.getJSONArray("students");
            List<Student> students = new ArrayList<>();
            for (int j = 0; j < studs.length(); j++) {
                students.add(deserializeStudent(studs.getJSONObject(j)));
            }
            project.setStudents(students);
        }

        return project;
    }

    public static synchronized Jury deserializeJury(JSONObject json) throws Exception{
       Jury jury = new Jury();

        if (json.has("idJury")){
            jury.setIdJury(json.getInt("idJury"));
        }

        if (json.has("date")){
            jury.setDate(json.getString("date"));
        }

        if (json.has("info")){
            JSONObject jsonInfo = json.getJSONObject("info");
            if (jsonInfo.has("members")) {
                JSONArray membersJson = jsonInfo.getJSONArray("members");
                List<Student> members = new ArrayList<Student>();
                for (int i = 0; i< membersJson.length(); i++){
                    members.add(deserializeStudent(membersJson.getJSONObject(i)));
                }
                jury.setMembers(members);
            }
            if (jsonInfo.has("projects")) {
                JSONArray projectsJson = jsonInfo.getJSONArray("projects");
                List<Project> projects = new ArrayList<Project>();
                for (int i = 0; i< projectsJson.length(); i++){
                    projects.add(deserializeProject(projectsJson.getJSONObject(i)));
                }
                jury.setProjects(projects);
            }

        }
       return jury;

    }

    public static synchronized Supervisor deserializeSupervisor(JSONObject json) throws Exception {
        Supervisor supervisor = new Supervisor();

        if (json.has("forename"))
            supervisor.setForename(json.getString("forename"));
        if (json.has("surname"))
            supervisor.setSurname(json.getString("surname"));

        return supervisor;
    }

    public static synchronized Student deserializeStudent(JSONObject json) throws Exception {
        Student student = new Student();

        if (json.has("userId"))
            student.setUserId(json.getInt("userId"));
        if (json.has("forename"))
            student.setForename(json.getString("forename"));
        if (json.has("surname"))
            student.setSurname(json.getString("surname"));

        return student;
    }

   /*

    public static synchronized Mark deserializeMark(JSONObject json) throws Exception {
        Mark mark = new Mark();

        if (json.has("userId"))
            mark.setIdStudent(json.getInt("userId"));
        if (json.has("forename"))
            mark.setForename(json.getString("forename"));
        if (json.has("surname"))
            mark.setSurname(json.getString("surname"));
        if (json.has("mynote"))
            mark.setMark(json.getInt("mynote"));
        if (json.has("avgnote"))
            mark.setAverage(json.getInt("avgnote"));

        return mark;
    }*/
/*
    public List<Project> DeserializeProject(String response){
        boolean resultBool = false;
        if (response != null) {
            try {
                JSONObject jsonObj = new JSONObject(response);
                String results = jsonObj.getString("result");
                if (results.equals("OK")){
                    List<Project> projects = new ArrayList<Project>();
                    JSONArray projectsJson = jsonObj.getJSONArray("projects");
                    //looping on all projects

                    for (int i = 0; i < projectsJson.length(); i++) {
                        JSONObject c = projectsJson.getJSONObject(i);
                        if (c.has("idProject")) {
                            int idProject = c.getInt("idProject");
                        }
                        if (c.has("title")) {
                            String title = c.getString("title");
                        }
                        if (c.has("descrip")) {
                            String descrip = c.getString("descrip");
                        }
                        if (c.has("poster")) {
                            boolean poster = c.getBoolean("poster");
                        }
                        if (c.has("supervisor")) {
                            JSONObject s = projectsJson.getJSONObject(i);
                            Super poster = c.getBoolean("poster");
                        }


                        private Supervisor supervisor;
                        private int confid;
                        private List<Student> students;


                    }
                    String token = jsonObj.getString("token");
                    this.token = token;
                    resultBool = true;
                }else{
                    String error = jsonObj.getString("error");
                    this.error = error;

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return resultBool;

    }*/
}
