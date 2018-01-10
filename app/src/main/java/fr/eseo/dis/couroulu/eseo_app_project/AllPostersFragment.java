package fr.eseo.dis.couroulu.eseo_app_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AllPostersFragment extends Fragment {

    private Button buttonMoreInformations;
    private TextView txtTitleProjectsView;
    private TextView txtDescriptionProjectsView;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_posters, container, false);
        //Instancier vos composants graphique ici (faîtes vos findViewById)
        txtDescriptionProjectsView = (TextView) view.findViewById(R.id.txtDescriptionProjectsView);
        txtTitleProjectsView = (TextView) view.findViewById(R.id.txtTitleProjectsView);

        buttonMoreInformations = (Button) view.findViewById(R.id.ButtonMoreInformations);
        buttonMoreInformations.setOnClickListener(btnListenerMoreInfo);
        return view;
    }

    private View.OnClickListener btnListenerMoreInfo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("bouton cliqué", txtDescriptionProjectsView.getText().toString());

        }
    };
}
