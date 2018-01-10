package fr.eseo.dis.couroulu.eseo_app_project.Communication;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Loulou on 10/01/2018.
 */


public class ResultLogin {

    private String result;
    private String token;
    private String error;

    public ResultLogin() {
        super();

    }

    public ResultLogin(String result, String token, String error) {
        super();
        this.result = result;
        this.token=token;
        this.error=error;

    }

    public Boolean Deserialize(String response){
        boolean resultBool = false;
        if (response != null) {
            try {
                JSONObject jsonObj = new JSONObject(response);
                String results = jsonObj.getString("result");
                this.result=results;
                if (results.equals("OK")){
                    String token = jsonObj.getString("token");
                    this.token = token;
                    resultBool = true;
                }else{
                    String error = jsonObj.getString("error");
                    this.error = error;

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return resultBool;

    }



    public String getError() {
        return error;
    }

    public String getResult() {
        return result;
    }

    public String getToken() {
        return token;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setToken(String token) {
        this.token = token;
    }
}