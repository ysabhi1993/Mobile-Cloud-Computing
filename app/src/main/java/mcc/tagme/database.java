package mcc.tagme;

/**
 * Created by suryanarayana on 5/12/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;

import static android.R.attr.name;
import static mcc.tagme.LoginActivity.email;

public class database extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView txtDetails;
    private EditText inputName, inputEmail;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.database_interface);

        // Displaying toolbar icon
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

//        txtDetails = (TextView) findViewById(R.id.txt_user);
//        inputName = (EditText) findViewById(R.id.name);
//        inputEmail = (EditText) findViewById(R.id.email);
//        btnSave = (Button) findViewById(R.id.btn_save);

       Intent intent = getIntent();
        Double longi = intent.getDoubleExtra("getLongitude",0);
        Double lati = intent.getDoubleExtra("getLatitude",0);


        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");


        String name = LoginActivity.email;
        String password = LoginActivity.password;
        final String imei = latlang.shareIMEI;
        final Double sharelongval = latlang.sharelongitudeBest;
        final Double sharelatval = latlang.shareLatitudeBest;

        if (TextUtils.isEmpty(userId) || userId == null) {
            createUser(LoginActivity.email,LoginActivity.password, NewsFragment.positionid,latlang.shareIMEI,sharelongval,sharelatval);
                } else {
                    updateUser(LoginActivity.email,LoginActivity.password, NewsFragment.positionid,imei);
                }


        // app_title change listener
//        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.e(TAG, "App title updated");
//
//                String appTitle = dataSnapshot.getValue(String.class);
//
//                // update toolbar title
//              //  getSupportActionBar().setTitle(appTitle);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.e(TAG, "Failed to read app title value.", error.toException());
//            }
//        });

        // Save / update the user
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                // Check for already existed userId
//                if (TextUtils.isEmpty(userId)) {
//                   createUser(LoginActivity.email,LoginActivity.password, NewsFragment.positionid,latlang.shareIMEI,sharelongval,sharelatval);
//                } else {
//                    updateUser(LoginActivity.email,LoginActivity.password, NewsFragment.positionid, imei);
//                }
//            }
//        });

//        toggleButton();
    }

    // Changing button text
//    private void toggleButton() {
//        if (TextUtils.isEmpty(userId)) {
//            btnSave.setText("Save");
//        } else {
//            btnSave.setText("Update");
//        }
//    }

    /**
     * Creating new user node under 'users'
     */
    private void createUser(String email, String password, int positionid ,String shareIMEI, Double sharelongval , Double sharelatval) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }
        String eventname;
        if (positionid == 0){
             eventname = "MCC";
        }
        else{
             eventname = "Pattern Recognition";
        }

        user user1 = new user(email, password , eventname , shareIMEI ,sharelongval ,  sharelatval);

        mFirebaseDatabase.child(userId).setValue(user1);
        Intent intent = new Intent(database.this, menu.class);
        startActivity(intent);
        finish();

       //addUserChangeListener();
    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user user = dataSnapshot.getValue(mcc.tagme.user.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                //Log.e(TAG, "User data is changed!" + user.name + ", " + user.email);

                // Display newly updated name and email
               // txtDetails.setText(user.name + ", " + user.email);

                // clear edit text
                inputEmail.setText("");
                inputName.setText("");

//                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateUser(String email,String password, int positionid,String imei) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(email))
            mFirebaseDatabase.child(userId).child("name").setValue(name);

        if (!TextUtils.isEmpty(password))
            mFirebaseDatabase.child(userId).child("password").setValue(password);

        if (!(positionid == 0 || positionid == 1) )
            mFirebaseDatabase.child(userId).child("registerid").setValue(positionid);

        if (!TextUtils.isEmpty(imei))
            mFirebaseDatabase.child(userId).child("password").setValue(password);





    }
}

