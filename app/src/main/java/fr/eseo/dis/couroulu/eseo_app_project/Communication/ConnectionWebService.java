package fr.eseo.dis.couroulu.eseo_app_project.Communication;

import android.content.Context;
import android.os.StrictMode;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import fr.eseo.dis.couroulu.eseo_app_project.R;

/**
 * Created by Loulou on 20/12/2017.
 */


public class ConnectionWebService {

    private static final String WEB_SERVICE_URL = "https://192.168.4.10/www/pfe/webservice.php";
    private static final String LOGON_WEB_SERVICE = "LOGON";
    private static final String LIPRJ_WEB_SERVICE = "LIPRJ";
    private static final String MYPRJ_WEB_SERVICE = "MYPRJ";



    private Context activityContext;
    private SSLContext sslContext;

    public ConnectionWebService(Context activityContext) {
        this.activityContext = activityContext;
        getSSLcontext();
    }

    public void getSSLcontext() {

        try{
            // Load CAs from an InputStream
            // (could be from a resource or ByteArrayInputStream or ...)
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            // From https://www.washington.edu/itconnect/security/ca/load-der.crt
            InputStream caInput = new BufferedInputStream(this.activityContext.getResources().openRawResource(R.raw.chain));
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
            } finally {
                caInput.close();
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            this.sslContext = SSLContext.getInstance("TLS");
            this.sslContext.init(null, tmf.getTrustManagers(), null);

        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }



    public String getJWTconnection(String login, String password) {

        URL url;
        //JsonReader jsonResponse = null;
        String response = null;
        try {
            url = new URL(WEB_SERVICE_URL+"?q="+LOGON_WEB_SERVICE+"&user="+login+"&pass="+password);
            HttpsURLConnection urlConnection;
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setSSLSocketFactory(this.sslContext.getSocketFactory());
            urlConnection.connect();
            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                urlConnection.getInputStream();
            }

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            response = convertStreamToString(in);
            //jsonResponse = new JsonReader(new InputStreamReader(urlConnection.getInputStream()));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String getListInformationOfAllProjects(String login, String token){

        URL url;
        //JsonReader jsonResponse = null;
        String response = null;
        try {
            url = new URL(WEB_SERVICE_URL+"?q="+LIPRJ_WEB_SERVICE+"&user="+login+"&token="+token);
            HttpsURLConnection urlConnection;
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setSSLSocketFactory(this.sslContext.getSocketFactory());
            urlConnection.connect();
            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                urlConnection.getInputStream();
            }

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            response = convertStreamToString(in);
            //jsonResponse = new JsonReader(new InputStreamReader(urlConnection.getInputStream()));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    public String getProjectsWhereUserIsSupervisor(String login, String token){

        URL url;
        //JsonReader jsonResponse = null;
        String response = null;
        try {
            url = new URL(WEB_SERVICE_URL+"?q="+MYPRJ_WEB_SERVICE+"&user="+login+"&token="+token);
            HttpsURLConnection urlConnection;
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setSSLSocketFactory(this.sslContext.getSocketFactory());
            urlConnection.connect();
            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                urlConnection.getInputStream();
            }

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            response = convertStreamToString(in);
            //jsonResponse = new JsonReader(new InputStreamReader(urlConnection.getInputStream()));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
