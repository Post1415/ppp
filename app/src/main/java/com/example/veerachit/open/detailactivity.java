package com.example.veerachit.open;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.veerachit.open.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class detailactivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference("message");
    public DatabaseReference testapp;
    public Button btn_click;
    public EditText txt_fullname,txt_topic,txt_content;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        testapp = database.getReference().child("Message");

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        txt_fullname = (EditText)findViewById(R.id.txt_fullname);
        txt_topic = (EditText)findViewById(R.id.txt_topic);
        txt_content = (EditText)findViewById(R.id.txt_content);
        progressBar.setVisibility(View.GONE);

        btn_click = (Button)findViewById(R.id.btn_submit);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String get_fullname = txt_fullname.getText().toString();
                String get_topic = txt_topic.getText().toString();
                String get_content = txt_content.getText().toString();

                if (TextUtils.isEmpty(get_fullname)) {
                    Toast.makeText(getApplicationContext(), "Please fill the form!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(get_topic)) {
                    Toast.makeText(getApplicationContext(), "Please fill the Topic!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(get_content)) {
                    Toast.makeText(getApplicationContext(), "Please fill the Content!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //progressBar.setVisibility(View.VISIBLE);
                sendToFireBase(get_fullname,get_topic,get_content);
                //ref.push().setValue(friendly);
                //progressBar.setVisibility(View.GONE);
            }
        });


    }
    private void sendToFireBase(String get_fullname, String get_topic, String
            get_content) {
        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference childRef;
        DatabaseReference listTopic;
        DatabaseReference listContent;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        childRef = ref.child(get_fullname);
        listTopic = childRef.child("topic");
        listTopic.setValue(get_topic);
        listContent = childRef.child("content");
        listContent.setValue(get_content);
        progressBar.setVisibility(View.GONE);
    }

}

