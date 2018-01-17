package fr.eseo.dis.couroulu.eseo_app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.couroulu.eseo_app_project.Affichage.EditNoteAdaptateur;
import fr.eseo.dis.couroulu.eseo_app_project.Affichage.ProjectAdaptateur;
import fr.eseo.dis.couroulu.eseo_app_project.Communication.ConnectionWebService;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Deserializer;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Note;
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
        final String idProject = (String) intent.getStringExtra("idProject");
        final List<Note> notes = getNotes(idProject);
        final EditNoteAdaptateur adapter = new EditNoteAdaptateur(this, notes);
        mListView.setAdapter(adapter);


        Button buttonUpdateNote = (Button) findViewById(R.id.buttonUpdateNoteStudent);
        buttonUpdateNote.setOnClickListener(new View.OnClickListener() {
            // Fonction pour lorsque click imageView Eseo go to home Page
            public void onClick(View v) {

                for(int i = 0; i < adapter.getCount(); i++){
                    v = mListView.getChildAt(i);
                    EditText noteStudent = (EditText) v.findViewById(R.id.noteStudent);
                    String noteUpdate = String.valueOf(noteStudent.getText());
                    getUpdateNote(idProject,String.valueOf(notes.get(i).getIdUser()),noteUpdate);

                }



                Intent appInfo = new Intent(v.getContext(), HomeActivity.class);
                startActivity(appInfo);

            }
        });
    }

    private List<Note> getNotes(String idProject) {

        String projectsStr = ConnectionWebService.getInstance(this).getNotesFromProject(idProject);
        List<Note> notes = new ArrayList<Note>();

        if (projectsStr != null) {

            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(projectsStr);
                String results = jsonObj.getString("result");
                if (results.equals("OK")) {
                    JSONArray projectsJson = jsonObj.getJSONArray("notes");
                    //looping on all notes
                    for (int i = 0; i < projectsJson.length(); i++) {

                        Note note = new Note();
                        note = Deserializer.deserializeNote(projectsJson.getJSONObject(i));
                        notes.add(note);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return notes;
    }

    private void getUpdateNote(String idProject, String idUser, String note){
        ConnectionWebService.getInstance(this).noteStudent(idProject,idUser,note);
    }



}
