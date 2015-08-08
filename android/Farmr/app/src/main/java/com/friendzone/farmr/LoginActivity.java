package com.friendzone.farmr;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.friendzone.farmr.utils.ConnectionDetector;
import com.friendzone.farmr.utils.Constants;
import com.friendzone.farmr.utils.JSONParser;
import com.friendzone.farmr.utils.ProgressGenerator;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements ProgressGenerator.OnCompleteListener {

    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";
    EditText editEmail, editPassword;
    ActionProcessButton btnSignIn;
    ProgressGenerator progressGenerator;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActionBarTitle(getResources().getString(R.string.log_in_to_farmr));

        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);

        progressGenerator = new ProgressGenerator(this);
        btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean(EXTRAS_ENDLESS_MODE)) {
            btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        } else {
            btnSignIn.setMode(ActionProcessButton.Mode.PROGRESS);
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editEmail.getText().toString().isEmpty()
                        || editEmail.getText().toString().length() == 0) {
                    editEmail.requestFocus();
                    editEmail.setError("This is required");

                } else if (editPassword.getText().toString().isEmpty()
                        || editPassword.getText().toString().length() == 0) {
                    editPassword.requestFocus();
                    editPassword.setError("This is required");
                } else {

                    if(ConnectionDetector.hasNetworkConnection(LoginActivity.this)) {
                        LogInAsyncTask task = new LogInAsyncTask(editEmail.getText().toString(),
                                editPassword.getText().toString());
                        task.execute();
                    } else {
                        Toast.makeText(getBaseContext(),
                                "No internet connection. Please try again.",
                                Toast.LENGTH_SHORT).show();
                    }



                }
            }
        });

    }

    @Override
    public void onComplete(int code) {

        if(code == 0) {
            btnSignIn.setText("Unauthorised User");
            btnSignIn.setBackgroundColor(getResources().getColor(R.color.red_error));
        }

    }

    public class LogInAsyncTask extends AsyncTask<Void, Void, Integer> {

        String email, password, response;
        int code, userType = 0;

        String TAG = "LogInAsyncTask";

        JSONParser jsonParser = new JSONParser();
        JSONObject json = null;

        public LogInAsyncTask(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Void... params) {

            try {

                JSONObject jsonObj = new JSONObject();
                jsonObj.put(Constants.EMAIL, email);
                jsonObj.put(Constants.PASSWORD, password);

                response = jsonParser.makePostHttpRequest(Constants.LOG_IN,
                        Constants.APP_JSON, jsonObj.toString());
                Log.e(TAG, "" + jsonObj.toString() + ": " + response);

                String responseHeaders = response.split("] ")[0];
                responseHeaders = "" + responseHeaders.substring(1);

                JSONObject jsonHeaders = new JSONObject(responseHeaders);
                edit.putString(Constants.ACCESS_TOKEN,
                        jsonHeaders.getString(Constants.ACCESS_TOKEN));
                edit.putString(Constants.CLIENT,
                        jsonHeaders.getString(Constants.CLIENT));
                edit.putString(Constants.UID,
                        jsonHeaders.getString(Constants.UID));
                edit.apply();

                try {
                    json = new JSONObject(response.split("] ")[1]);
                    Log.d("", "Response: " + json.toString());
                    code = json.getInt("code");

                    JSONObject data = json.getJSONObject("data");
                    userType = data.getInt("user_type");
                } catch (JSONException e) {
                    Log.e(TAG, "JSONException: " + e.toString());
                }

            } catch (Exception e) {

                Log.e(TAG, "Exception: " + e.toString());
            }

            return code;

        }

        @Override
        protected void onPostExecute(Integer result) {

            if (result != null) {

                progressGenerator.start(btnSignIn, result);

                if(result == 1) {
                    Log.e(TAG, "userType: " + userType);
                    edit.putBoolean(Constants.IS_LOGGED_IN, true);
                    edit.apply();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra(Constants.USER_TYPE, userType);
                    startActivity(i);
                    finish();
                } else {
//                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }


        }


    }

}