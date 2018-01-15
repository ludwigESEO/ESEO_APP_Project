package fr.eseo.dis.couroulu.eseo_app_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;

import fr.eseo.dis.couroulu.eseo_app_project.Communication.ConnectionWebService;

public class FullPosterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_poster);

        Intent intent = getIntent();
        String idProject = intent.getStringExtra("idProject");


        ImageView imgPoster = (ImageView) findViewById(R.id.imgPoster);

        imgPoster.setImageBitmap(getPoster(idProject,"FULL"));
    }

    private Bitmap getPoster(String idProject, String style){

        InputStream projectPoster = ConnectionWebService.getInstance(this).getPosterOfProjectWithStyle(idProject,style);
        Bitmap bmpParibas = BitmapFactory.decodeStream(projectPoster);

        return bmpParibas;

    }


}
