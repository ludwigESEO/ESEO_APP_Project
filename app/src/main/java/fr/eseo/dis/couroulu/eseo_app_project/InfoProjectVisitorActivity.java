package fr.eseo.dis.couroulu.eseo_app_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import fr.eseo.dis.couroulu.eseo_app_project.Communication.ConnectionWebService;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Project;

public class InfoProjectVisitorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_project_visitor);
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

        final String id_Project = String.valueOf(project.getIdProject());
        String style = "THUMB";

        ImageView imgPoster = (ImageView) findViewById(R.id.imgPoster);

        imgPoster.setImageBitmap(getPoster(id_Project,style));
        imgPoster.setOnClickListener(new View.OnClickListener() {
            // Fonction pour lorsque click imageView Eseo go to home Page
            public void onClick(View v) {

                Intent appInfo = new Intent(v.getContext(), FullPosterActivity.class);
                appInfo.putExtra("idProject", id_Project);
                startActivity(appInfo);

            }
        });
    }


    private Bitmap getPoster(String idProject, String style){

        InputStream projectPoster = ConnectionWebService.getInstance(this).getPosterOfProjectWithStyle(idProject,style);
        Bitmap bmpParibas = BitmapFactory.decodeStream(projectPoster);

        return bmpParibas;

    }
}
