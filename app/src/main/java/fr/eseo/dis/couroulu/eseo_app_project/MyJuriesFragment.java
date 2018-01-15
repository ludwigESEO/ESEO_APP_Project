package fr.eseo.dis.couroulu.eseo_app_project;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import fr.eseo.dis.couroulu.eseo_app_project.Affichage.MyJuriesAdaptateur;
import fr.eseo.dis.couroulu.eseo_app_project.Communication.ConnectionWebService;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Deserializer;
import fr.eseo.dis.couroulu.eseo_app_project.Data.Jury;


public class MyJuriesFragment extends Fragment {

    private HomeActivity homeActivity;
    private ListView mListView;
    private TextView mTextNoJury;

    public static MyJuriesFragment newInstance(HomeActivity homeActivity){

        MyJuriesFragment fragment = new MyJuriesFragment();
        fragment.homeActivity = homeActivity;
        return fragment;

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final List<Jury> juries = getMyJuries();
        View view ;
        if (!juries.isEmpty()) {
            view = inflater.inflate(R.layout.fragment_my_juries, container, false);
            mListView = (ListView) view.findViewById(R.id.listView);

            Log.d("juries", String.valueOf(juries.get(0).getMembers().size()));
            MyJuriesAdaptateur adapter = new MyJuriesAdaptateur(homeActivity, juries);
            mListView.setAdapter(adapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent appInfo = new Intent(homeActivity, ListProjectFromJuryActivity.class);
                    appInfo.putExtra("idJury", String.valueOf(juries.get(position).getIdJury()));
                    startActivity(appInfo);

                }
            });


        }else{
            view = inflater.inflate(R.layout.fragment_no_juries, container, false);
            mTextNoJury = (TextView) view.findViewById(R.id.textViewNoJuries);

        }

        return view;
    }

    private List<Jury> getMyJuries() {

        String juriesStr = ConnectionWebService.getInstance(homeActivity).getListOfJuriesForAuser();
        List<Jury> juries = new ArrayList<Jury>();

        if (juriesStr != null) {

            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(juriesStr);
                String results = jsonObj.getString("result");
                if (results.equals("OK")) {
                    JSONArray juriesJson = jsonObj.getJSONArray("juries");
                    //looping on my juries
                    for (int i = 0; i < juriesJson.length(); i++) {
                        Jury jury = new Jury();
                        jury = Deserializer.deserializeJury(juriesJson.getJSONObject(i));
                        juries.add(jury);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return juries;
    }
}
