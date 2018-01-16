package fr.eseo.dis.couroulu.eseo_app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import fr.eseo.dis.couroulu.eseo_app_project.Affichage.EditNoteAdaptateur;
import fr.eseo.dis.couroulu.eseo_app_project.Affichage.ProjectAdaptateur;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Project;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Student;

public class EditNoteActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        mListView = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent();
        final List<Student> student = (List<Student>) intent.getSerializableExtra("students");




        EditNoteAdaptateur adapter = new EditNoteAdaptateur(this, student);
        mListView.setAdapter(adapter);


    }
}
