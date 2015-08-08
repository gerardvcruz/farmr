package com.friendzone.farmr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.friendzone.farmr.utils.ProgressGenerator;

public class LoginActivity extends BaseActivity implements ProgressGenerator.OnCompleteListener {

    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";
    EditText editEmail, editPassword;

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

        final ProgressGenerator progressGenerator = new ProgressGenerator(this);
        final ActionProcessButton btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);
        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.getBoolean(EXTRAS_ENDLESS_MODE)) {
            btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        } else {
            btnSignIn.setMode(ActionProcessButton.Mode.PROGRESS);
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editEmail.getText().toString().isEmpty()
                        || editEmail.getText().toString().length() == 0) {
                    editEmail.requestFocus();
                    editEmail.setError("This is required");

                } else if (editPassword.getText().toString().isEmpty()
                        || editPassword.getText().toString().length() == 0) {
                    editPassword.requestFocus();
                    editPassword.setError("This is required");
                } else {
                    progressGenerator.start(btnSignIn);
                    btnSignIn.setEnabled(false);
                    editEmail.setEnabled(false);
                    editPassword.setEnabled(false);
                }
            }
        });

    }

    @Override
    public void onComplete() {

        // TODO check user type
        // Supplier = 1
        // Buyer = 2

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}