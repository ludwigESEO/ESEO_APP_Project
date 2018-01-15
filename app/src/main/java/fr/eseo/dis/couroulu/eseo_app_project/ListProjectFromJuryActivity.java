package fr.eseo.dis.couroulu.eseo_app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.couroulu.eseo_app_project.Affichage.ProjectAdaptateur;
import fr.eseo.dis.couroulu.eseo_app_project.Affichage.ProjectFromJuryAdaptateur;
import fr.eseo.dis.couroulu.eseo_app_project.Communication.ConnectionWebService;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Deserializer;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Project;

public class ListProjectFromJuryActivity extends AppCompatActivity {

    private ListView mListView;
    private ListProjectFromJuryActivity listProjectFromJuryActivity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_project_from_jury);
        mListView = (ListView) findViewById(R.id.listViewJuryProject);
        Intent intent = getIntent();
        String idJury = intent.getStringExtra("idJury");
        final List<Project> projectFromJuryList = getProjectFromJury(idJury);
        ProjectFromJuryAdaptateur adapter = new ProjectFromJuryAdaptateur(this, projectFromJuryList);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent appInfo = new Intent(view.getContext(), InfoProjectActivity.class);
                appInfo.putExtra("idProject", String.valueOf(projectFromJuryList.get(position).getIdProject()));
                startActivity(appInfo);

            }
        });


    }

    private List<Project> getProjectFromJury(String juryId) {

        String projectsStr = ConnectionWebService.getInstance(this).getProjectFromJury(juryId);
        List<Project> projects = new ArrayList<Project>();

        if (projectsStr != null) {

            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(projectsStr);
                String results = jsonObj.getString("result");
                if (results.equals("OK")) {
                    JSONArray projectsJson = jsonObj.getJSONArray("projects");
                    //looping on all projects
                    for (int i = 0; i < projectsJson.length(); i++) {

                        Project proj = new Project();
                        proj = Deserializer.deserializeProject(projectsJson.getJSONObject(i));
                        projects.add(proj);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return projects;
    }
}

