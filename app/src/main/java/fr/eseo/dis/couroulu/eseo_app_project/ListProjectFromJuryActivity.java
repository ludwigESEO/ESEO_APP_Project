package fr.eseo.dis.couroulu.eseo_app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import fr.eseo.dis.couroulu.eseo_app_project.Affichage.ProjectAdaptateur;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Project;

public class ListProjectFromJuryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.activity_list_project_from_jury, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);
        List<Project> projects = getAllProject();
        ProjectAdaptateur adapter = new ProjectAdaptateur(homeActivity, projects);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent appInfo = new Intent(homeActivity, InfoProjectActivity.class);
                startActivity(appInfo);

            }
        });


        return view;
    }
}
