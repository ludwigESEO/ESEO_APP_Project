package fr.eseo.dis.couroulu.eseo_app_project.Affichage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.eseo.dis.couroulu.eseo_app_project.Data.Jury;
import fr.eseo.dis.couroulu.eseo_app_project.R;

/**
 * Created by florent on 11/01/18.
 */

public class MyJuriesAdaptateur extends ArrayAdapter<Jury> {

    public MyJuriesAdaptateur(Context context, List<Jury> juries) {
        super(context, 0, juries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_juries,parent, false);
        }

        MyJuriesAdaptateur.MyJuriesViewHolder viewHolder = (MyJuriesAdaptateur.MyJuriesViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MyJuriesAdaptateur.MyJuriesViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.members = (TextView) convertView.findViewById(R.id.members);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Jury jury = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.date.setText("Date du Jury : " + jury.getDate());
        viewHolder.members.setText(jury.toStringListMembers());

        return convertView;
    }

    private class MyJuriesViewHolder{
        public TextView date;
        public TextView members;
    }

}
