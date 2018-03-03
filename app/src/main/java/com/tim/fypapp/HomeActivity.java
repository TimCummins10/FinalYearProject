package com.tim.fypapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tim.fypapp.Model.UserInformation;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database;
    DatabaseReference users;
    TextView welcomeUser;

    FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = FirebaseDatabase.getInstance();
        users = database.getReference().child("fullName");
        mAuth = FirebaseAuth.getInstance();

        //Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);


        welcomeUser = (TextView) findViewById(R.id.userLoggedIn);
        findViewById(R.id.takeAttendance).setOnClickListener(this);
        findViewById(R.id.viewAllClasses).setOnClickListener(this);



/*
        // Get a reference to your user
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Users");

// Attach a listener to read the data at your profile reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInformation profile = dataSnapshot.getValue(UserInformation.class);
                System.out.println(profile.getFullName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

*/

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // String userLoggedIn = dataSnapshot.getValue().toString();
               // welcomeUser.setText("Welcome " + userLoggedIn);


                UserInformation welcome = dataSnapshot.getValue(UserInformation.class);
                System.out.print("Welcome");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, SignInActivity.class));

                break;

        }

        return true;
    }



        @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, SignInActivity.class));
        }

    }


    @Override
    public void onClick(View view) {

        switch(view.getId()) {

            case R.id.takeAttendance:
                finish();
                startActivity(new Intent(this, TakeAttendanceActivity.class));
                break;

            case R.id.viewAllClasses:
                finish();
                startActivity(new Intent(this, ViewClassesActivity.class));
                break;
        }
    }
}