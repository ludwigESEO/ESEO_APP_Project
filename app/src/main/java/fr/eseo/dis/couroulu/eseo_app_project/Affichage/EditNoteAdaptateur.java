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
import fr.eseo.dis.couroulu.eseo_app_project.Data.Note;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Student;
import fr.eseo.dis.couroulu.eseo_app_project.R;

/**
 * Created by Loulou on 16/01/2018.
 */

public class EditNoteAdaptateur extends ArrayAdapter<Note>{

    public EditNoteAdaptateur(Context context, List<Note> notes) {
        super(context, 0, notes);
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
            viewHolder.avgnote = (TextView) convertView.findViewById(R.id.idAvgNote);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Note note = getItem(position);

        String avgNoteNew = note.getAvgnote();
        if (avgNoteNew.length()>=5){
            avgNoteNew = avgNoteNew.substring(0,4);
        }


        viewHolder.forename.setText("Name : " + note.getForename());
        viewHolder.surname.setText(note.getSurname());
        viewHolder.avgnote.setText("Average Note : " + avgNoteNew);

        if (viewHolder.note.getText() != null && viewHolder.note.getText().toString().isEmpty()) {
            viewHolder.note.setText(note.getMynote());
        }

        return convertView;
    }

    private class MyJuriesViewHolder{
        public TextView forename;
        public TextView surname;
        public EditText note;
        public TextView avgnote;
    }
}
