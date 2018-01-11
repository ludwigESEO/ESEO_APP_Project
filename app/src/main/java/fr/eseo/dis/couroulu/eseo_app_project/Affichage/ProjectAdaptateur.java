package fr.eseo.dis.couroulu.eseo_app_project.Affichage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.eseo.dis.couroulu.eseo_app_project.Data.Project;
import fr.eseo.dis.couroulu.eseo_app_project.R;

/**
 * Created by florent on 10/01/18.
 */

public class ProjectAdaptateur extends ArrayAdapter<Project> {


        //tweets est la liste des models à afficher
        public ProjectAdaptateur(Context context, List<Project> projects) {
            super(context, 0, projects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_project,parent, false);
            }

            ProjectViewHolder viewHolder = (ProjectViewHolder) convertView.getTag();
            if(viewHolder == null){
                viewHolder = new ProjectViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.descrip = (TextView) convertView.findViewById(R.id.descrip);
                convertView.setTag(viewHolder);
            }

            //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
            Project project = getItem(position);

            //il ne reste plus qu'à remplir notre vue
            viewHolder.title.setText(project.getTitle());
            viewHolder.descrip.setText(project.getDescrip());

            return convertView;
        }

        private class ProjectViewHolder{
            public TextView title;
            public TextView descrip;
        }

}
