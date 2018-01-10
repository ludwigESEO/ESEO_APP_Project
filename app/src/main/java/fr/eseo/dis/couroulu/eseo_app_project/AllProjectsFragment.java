package fr.eseo.dis.couroulu.eseo_app_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.couroulu.eseo_app_project.Affichage.ProjectAdaptateur;
import fr.eseo.dis.couroulu.eseo_app_project.Communication.ConnectionWebService;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Deserializer;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Project;


public class AllProjectsFragment extends Fragment {

    private Button buttonMoreInformations;
    private TextView txtTitleProjectsView;
    private TextView txtDescriptionProjectsView;
    private HomeActivity homeActivity;
    private ListView mListView;

    public static AllProjectsFragment newInstance(HomeActivity homeActivity){

        AllProjectsFragment fragment = new AllProjectsFragment();

        fragment.homeActivity = homeActivity;

        return fragment;


    }


    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_posters, container, false);
        //Instancier vos composants graphique ici (faîtes vos findViewById)
        //setContentView(R.layout.activity_home);
       // mListView = (ListView) findViewById(R.id.listView);

        List<Project> projects = getAllProject();

       // ProjectAdaptateur adapter = new ProjectAdaptateur(MainActivity.this, projects);
        //mListView.setAdapter(adapter);
       // for(Project project : projects) {
         //   txtDescriptionProjectsView = (TextView) view.findViewById(R.id.txtDescriptionProjectsView);
           // txtDescriptionProjectsView.setText(project.getDescrip());
           // txtTitleProjectsView = (TextView) view.findViewById(R.id.txtTitleProjectsView);

        //}
        //buttonMoreInformations = (Button) view.findViewById(R.id.ButtonMoreInformations);
        //buttonMoreInformations.setOnClickListener(btnListenerMoreInfo);
        return view;
    }

    private View.OnClickListener btnListenerMoreInfo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("bouton cliqué", txtDescriptionProjectsView.getText().toString());

        }
    };

    private List<Project> getAllProject() {

        String projectsStr = ConnectionWebService.getInstance(homeActivity).getListInformationOfAllProjects();
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
                            Deserializer.deserializeProject(projectsJson.getJSONObject(i));
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
