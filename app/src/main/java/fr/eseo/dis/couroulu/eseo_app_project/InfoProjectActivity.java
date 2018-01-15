package fr.eseo.dis.couroulu.eseo_app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fr.eseo.dis.couroulu.eseo_app_project.Data.Project;

public class InfoProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_project);
        Intent intent = getIntent();
        Project project = (Project) intent.getSerializableExtra("project");

        TextView title = (TextView) findViewById(R.id.title);
        TextView desc = (TextView) findViewById(R.id.descrip);
        TextView confid = (TextView) findViewById(R.id.confid);
        TextView student = (TextView) findViewById(R.id.student);
        TextView supervisor = (TextView) findViewById(R.id.supervisor);

        title.setText(project.getTitle());
        desc.setText(project.getDescrip());
        confid.setText(String.valueOf(project.getConfid()));
        student.setText(project.toStringListStudent());
        supervisor.setText(project.getSupervisor().toStringSupervisor());



    }
}
