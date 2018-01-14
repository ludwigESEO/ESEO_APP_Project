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
 * Created by florent on 12/01/18.
 */

public class ProjectFromJuryAdaptateur extends ArrayAdapter<Project> {

    public ProjectFromJuryAdaptateur(Context context, List<Project> projects) {
        super(context, 0, projects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_project_from_jury,parent, false);
        }

        ProjectFromJuryAdaptateur.ProjectViewHolder viewHolder = (ProjectFromJuryAdaptateur.ProjectViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ProjectFromJuryAdaptateur.ProjectViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.descrip = (TextView) convertView.findViewById(R.id.descrip);
            viewHolder.confid = (TextView) convertView.findViewById(R.id.confid);
            viewHolder.supervisor = (TextView) convertView.findViewById(R.id.supervisor);
            viewHolder.student = (TextView) convertView.findViewById(R.id.student);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Project project = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.title.setText(project.getTitle());
        viewHolder.descrip.setText(project.getDescrip());
        viewHolder.confid.setText(project.getConfid());
        viewHolder.supervisor.setText(project.getSupervisor().getForename()+" "+ project.getSupervisor().getSurname());
        viewHolder.student.setText(project.toStringListStudent());

        return convertView;
    }

    private class ProjectViewHolder{
        public TextView title;
        public TextView descrip;
        public TextView confid;
        public TextView supervisor;
        public TextView student;
    }

}
