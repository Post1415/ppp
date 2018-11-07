package com.example.veerachit.open;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ownerRegister extends AppCompatActivity {

    public DatabaseReference testapp;
    EditText ownerName, ownerID, ownerPassword, ownerInvitationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_register);

        ownerName = (EditText) findViewById(R.id.name_owner_edit_text);
        ownerID = (EditText) findViewById(R.id.username_owner_edit_view);
        ownerPassword = (EditText) findViewById(R.id.password_owner_edit_view);
        ownerInvitationCode = (EditText) findViewById(R.id.invitation_edit_text);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        testapp = database.getReference().child("User");

        Button c = (Button) findViewById(R.id.submit_button);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ownerName.getText().toString();
                String ID = ownerID.getText().toString();
                String password = ownerPassword.getText().toString();
                String InvitationCode = ownerInvitationCode.getText().toString();
                registerid registerid = new registerid(name, ID, password);
                testapp.push().setValue(registerid);

                Intent intent = new Intent(ownerRegister.this, signIn.class);
                startActivity(intent);
            }
        });



    }
}
