package fr.eseo.dis.couroulu.eseo_app_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.couroulu.eseo_app_project.Affichage.MyProjectAdaptateur;
import fr.eseo.dis.couroulu.eseo_app_project.Communication.ConnectionWebService;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Deserializer;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Project;


public class MyProjectsFragment extends Fragment {

    private HomeActivity homeActivity;
    private ListView mListView;
    private TextView noTextSupervisor;

    public static MyProjectsFragment newInstance(HomeActivity homeActivity){

        MyProjectsFragment myProjectsFragment = new MyProjectsFragment();
        myProjectsFragment.homeActivity = homeActivity;
        return myProjectsFragment;
    }


    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view ;
        final List<Project> projects = getMyProject();

        if (!projects.isEmpty()) {
            view = inflater.inflate(R.layout.fragment_all_projects, container, false);
            mListView = (ListView) view.findViewById(R.id.listView);

            MyProjectAdaptateur adapter = new MyProjectAdaptateur(homeActivity, projects);
            mListView.setAdapter(adapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent appInfo = new Intent(homeActivity, InfoProjectVisitorActivity.class);
                    Project project = projects.get(position);
                    appInfo.putExtra("project", project);
                    startActivity(appInfo);

                }
            });
        }else{
            view = inflater.inflate(R.layout.fragment_no_supervisor, container, false);
            noTextSupervisor = (TextView) view.findViewById(R.id.textViewNoSupervisor);
        }

        return view;
    }

    private List<Project> getMyProject() {

        String projectsStr = ConnectionWebService.getInstance(homeActivity).getProjectsWhereUserIsSupervisor();
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
