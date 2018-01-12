package fr.eseo.dis.couroulu.eseo_app_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

    public static MyJuriesFragment newInstance(HomeActivity homeActivity){

        MyJuriesFragment fragment = new MyJuriesFragment();
        fragment.homeActivity = homeActivity;
        return fragment;

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_juries, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);
        List<Jury> juries = getMyJuries();
        Log.d("juries",String.valueOf(juries.get(0).getMembers().size()));
        MyJuriesAdaptateur adapter = new MyJuriesAdaptateur(homeActivity, juries);
        mListView.setAdapter(adapter);

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
