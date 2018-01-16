package fr.eseo.dis.couroulu.eseo_app_project.Affichage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import fr.eseo.dis.couroulu.eseo_app_project.Data.Jury;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Student;
import fr.eseo.dis.couroulu.eseo_app_project.R;

/**
 * Created by Loulou on 16/01/2018.
 */

public class EditNoteAdaptateur extends ArrayAdapter<Student> {

    public EditNoteAdaptateur(Context context, List<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_note,parent, false);
        }

        EditNoteAdaptateur.MyJuriesViewHolder viewHolder = (EditNoteAdaptateur.MyJuriesViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new EditNoteAdaptateur.MyJuriesViewHolder();
            viewHolder.forename = (TextView) convertView.findViewById(R.id.forename);
            viewHolder.surname = (TextView) convertView.findViewById(R.id.surname);
            viewHolder.note = (EditText) convertView.findViewById(R.id.noteStudent);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Student student = getItem(position);

        //il ne reste plus qu'à remplir notre vue

        viewHolder.forename.setText("Name : " + student.getForename());
        viewHolder.surname.setText(student.getSurname());
        viewHolder.note.setText("");

        return convertView;
    }

    private class MyJuriesViewHolder{
        public TextView forename;
        public TextView surname;
        public EditText note;
    }
}
