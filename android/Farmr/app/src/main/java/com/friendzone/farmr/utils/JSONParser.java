package com.friendzone.farmr.utils;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class JSONParser {

//    public HttpResponse makePostHttpRequest(String url, List<NameValuePair> params)
//            throws IOException, NoSuchAlgorithmException, KeyManagementException,
//            URISyntaxException, KeyStoreException, UnrecoverableKeyException,
//            ConnectTimeoutException, SocketTimeoutException {
//
//        DefaultHttpClient httpClient  = null;
//        HttpPost httpPost = null;
//
//        try {
//            HttpParams httpParameters = new BasicHttpParams();
//            HttpConnectionParams.setConnectionTimeout(httpParameters, 30000);
//            HttpConnectionParams.setSoTimeout(httpParameters, 30000);
//            httpClient = new DefaultHttpClient(httpParameters);
//
//            httpPost = new HttpPost(url);
//            httpPost.setEntity(new UrlEncodedFormEntity(params));
//        } catch (IOException e) {
//            Log.e("IOException", "" + e.toString());
//        }
//
//        return httpClient.execute(httpPost);
//
//    }
//
//    public HttpResponse makePostHttpRequest(String url, String header, List<NameValuePair> params)
//            throws IOException, NoSuchAlgorithmException, KeyManagementException,
//            URISyntaxException, KeyStoreException, UnrecoverableKeyException,
//            ConnectTimeoutException, SocketTimeoutException {
//
//        DefaultHttpClient httpClient  = null;
//        HttpPost httpPost = null;
//
//        try {
//            HttpParams httpParameters = new BasicHttpParams();
//            HttpConnectionParams.setConnectionTimeout(httpParameters, 30000);
//            HttpConnectionParams.setSoTimeout(httpParameters, 30000);
//            httpClient = new DefaultHttpClient(httpParameters);
//
//            httpPost = new HttpPost(url);
//            httpPost.setEntity(new UrlEncodedFormEntity(params));
//            httpPost.setHeader(Constants.TAG_API_KEY, header);
//        } catch (IOException e) {
//            Log.e("IOException", "" + e.toString());
//        }
//
//        return httpClient.execute(httpPost);
//
//    }

//    public HttpResponse makeGetHttpRequest(String url, String header, List<NameValuePair> params)
//            throws IOException, NoSuchAlgorithmException, KeyManagementException,
//            URISyntaxException, KeyStoreException, UnrecoverableKeyException,
//            ConnectTimeoutException, SocketTimeoutException {
//
//        DefaultHttpClient httpClient  = null;
//        HttpGet httpGet = null;
//
//            HttpParams httpParameters = new BasicHttpParams();
//            HttpConnectionParams.setConnectionTimeout(httpParameters, 30000);
//            HttpConnectionParams.setSoTimeout(httpParameters, 30000);
//            httpClient = new DefaultHttpClient(httpParameters);
//
//            String paramString = URLEncodedUtils.format(params, "utf-8");
//            url += "?" + paramString;
//            httpGet = new HttpGet(url);
//            httpGet.setHeader(Constants.TAG_API_KEY, header);
//
//        return httpClient.execute(httpGet);
//
//    }

//    public String getResponse(HttpResponse response) {
//
//        StringBuilder sb = new StringBuilder();
//
//        try {
//
//            InputStream is = response.getEntity().getContent();
//            StringBuilder out = new StringBuilder();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            for (String line = br.readLine(); line != null; line = br.readLine())
//                out.append(line);
//            br.close();
//
//            sb.append(out.toString());
//
//        }  catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return sb.toString();
//    }

    public String makeGetHttpRequest (String link) throws Exception {

        String response = "";

        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(30000);
        conn.setConnectTimeout(30000);
        conn.setRequestMethod("GET");
        conn.connect();

        InputStream is = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader
                (is, "UTF-8") );
        String data = null;
        while ((data = reader.readLine()) != null){
            response += data + "\n";
            Log.i("Response-GET", "" + response);
        }

        is.close();
        reader.close();

        if (conn != null) {
            conn.disconnect();
        }

        return response;

    }

//    public String makeGetHttpRequest (String link, String header, List<NameValuePair> params) throws Exception {
//
//        String paramString = URLEncodedUtils.format(params, "UTF-8");
//        String encodedParamString = URLDecoder.decode(paramString, "UTF-8");
//        link += "?" + encodedParamString;
//        String response = "";
//        Log.d("DEBUG", "" + link);
//
//        URL url = new URL(link);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setReadTimeout(30000);
//        conn.setConnectTimeout(30000);
//        conn.setRequestMethod("GET");
//        conn.addRequestProperty(Constants.TAG_API_KEY, header);
//        conn.connect();
//
//        InputStream is = conn.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader
//                    (is, "UTF-8") );
//        String data = null;
//        while ((data = reader.readLine()) != null){
//            response += data + "\n";
//            Log.i("Response-GET", "" + response);
//        }
//
//        is.close();
//        reader.close();
//
//        if (conn != null) {
//            conn.disconnect();
//        }
//
//        return response;
//
//    }


    public String makePostHttpRequest (String link, List<NameValuePair> params) throws Exception {

        String paramString = URLEncodedUtils.format(params, "UTF-8");
        String response = "";
        Log.d("DEBUG", "" + link);

        URL url = null;
        HttpURLConnection conn = null;

        try {
            url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);

            int statusCode = conn.getResponseCode();

            // Send post request
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(paramString);
            wr.flush();
            wr.close();

            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (is, "UTF-8") );
            String data = null;
            while ((data = reader.readLine()) != null){
                response += data + "\n";
                Log.i("Response", "" + response);
            }

            is.close();
            reader.close();

        } catch (Exception e){
            Log.e("Exception: ", "" + e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return response;

    }

    public String makePostHttpRequest (String link, String header, String entity) throws Exception {

        String paramString = entity;
        String response = "";

        String responseHeaders = "";

        Log.d("DEBUG", "" + link);

        URL url = null;
        HttpURLConnection conn = null;

        try {
            url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty(Constants.CONTENT_TYPE, header);

            // Send post request
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

            wr.writeBytes(paramString);
            wr.flush();
            wr.close();

            conn.connect();

            int status = conn.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {
                String ACCESS_TOKEN = conn.getHeaderField(Constants.ACCESS_TOKEN);
                String CLIENT = conn.getHeaderField(Constants.CLIENT);
                String UID = conn.getHeaderField(Constants.UID);
                responseHeaders = "[{'" + Constants.ACCESS_TOKEN + "' : '" + ACCESS_TOKEN + "', " +
                        "'" + Constants.CLIENT + "' : '" + CLIENT + "', '" + Constants.UID + "' : '"
                        + UID + "' }]";
            }

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (is, "UTF-8") );
            String data = "";
            while ((data = reader.readLine()) != null){
                response += data + "\n";
                Log.i("Response", "" + response);
            }

            is.close();
            reader.close();

        } catch (Exception e){
            Log.e("Exception: ", "" + e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return responseHeaders + " " +response;

    }


//    public String makePostHttpRequest (String link, String header, List<NameValuePair> params) throws Exception {
//
//        String paramString = URLEncodedUtils.format(params, "UTF-8");
//        //String encodedParamString = URLEncoder.encode(paramString, "UTF-8");
//        String response = "";
//        Log.d("DEBUG", "" + link);
//
//        URL url = null;
//        HttpURLConnection conn = null;
//
//        try {
//            url = new URL(link);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(30000);
//            conn.setConnectTimeout(30000);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty(Constants.TAG_API_KEY, header);
//
//            // Send post request
//            conn.setDoOutput(true);
//            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//            wr.writeBytes(paramString);
//            wr.flush();
//            wr.close();
//
//            conn.connect();
//
//            InputStream is = conn.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader
//                    (is, "UTF-8") );
//            String data = null;
//            while ((data = reader.readLine()) != null){
//                response += data + "\n";
//                Log.i("Response", "" + response);
//            }
//
//            is.close();
//            reader.close();
//
//        } catch (Exception e){
//            Log.e("Exception: ", "" + e.toString());
//        } finally {
//            if (conn != null) {
//                conn.disconnect();
//            }
//        }
//
//        return response;
//
//    }

//    public String makePostHttpRequest (String link, String header, String contentType,
//                                       List<NameValuePair> params) throws Exception {
//
//        String paramString = URLEncodedUtils.format(params, "UTF-8");
//        //String encodedParamString = URLEncoder.encode(paramString, "UTF-8");
//        String response = "";
//        Log.d("DEBUG", "" + link);
//
//        URL url = null;
//        HttpURLConnection conn = null;
//
//        try {
//            url = new URL(link);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(30000);
//            conn.setConnectTimeout(30000);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty(Constants.TAG_API_KEY, header);
//            conn.setRequestProperty(Constants.TAG_CONTENT_TYPE, contentType);
//
//            // Send post request
//            conn.setDoOutput(true);
//            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//            wr.writeBytes(paramString);
//            wr.flush();
//            wr.close();
//
//            conn.connect();
//
//            InputStream is = conn.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader
//                    (is, "UTF-8") );
//            String data;
//            while ((data = reader.readLine()) != null){
//                response += data + "\n";
//                Log.i("Response", "" + response);
//            }
//
//            is.close();
//            reader.close();
//
//        } catch (Exception e){
//            Log.e("Exception: ", "" + e.toString());
//        } finally {
//            if (conn != null) {
//                conn.disconnect();
//            }
//        }
//
//        return response;
//
//    }

	
}