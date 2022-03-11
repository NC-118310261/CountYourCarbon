package com.example.countyourcarbon00;
//Edit user profile data in firebase using Android studio
//https://www.youtube.com/watch?v=CMAup2xxsJw
//Add data to Cloud Firestore
//https://firebase.google.com/docs/firestore/manage-data/add-data

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class UpdateProfile extends AppCompatActivity {

    EditText txtName, txtUserEmail, txtUserPassword, txtUserSchool, txtUserAddress;
    Button btnUpdateUser;
    String currentUid;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    DocumentReference documentReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        txtName = findViewById(R.id.txtName);
        txtUserEmail = findViewById(R.id.txtUserEmail);
        txtUserPassword = findViewById(R.id.txtUserPassword);
        txtUserSchool = findViewById(R.id.txtUserSchool);
        txtUserAddress = findViewById(R.id.txtUserAddress);
        btnUpdateUser = findViewById(R.id.btnUpdateUser);

        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //String currentUid = user.getUid();

        //documentReference.collection("Users").document(currentUid);

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }

        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        /*
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.getResult().exists()) {
                            String nameResult = task.getResult().getString("name");
                            String emailResult = task.getResult().getString("email");
                            String passwordResult = task.getResult().getString("password");
                            String schoolResult = task.getResult().getString("school");
                            String addressResult = task.getResult().getString("address");

                            txtName.setText(nameResult);
                            txtUserEmail.setText(emailResult);
                            txtUserPassword.setText(passwordResult);
                            txtUserSchool.setText(schoolResult);
                            txtUserAddress.setText(addressResult);
                        }else{
                            Toast.makeText(UpdateProfile.this, "No profile", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        */
        // get email from home page
        Intent prevIntent = getIntent();
        String userEmail = prevIntent.getStringExtra("emailLoggedIn");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    com.example.countyourcarbon00.Users users = snapshot.getValue(com.example.countyourcarbon00.Users.class);
                    // System.out.println(emailLoggedIn); Working
                    if (userEmail.equals((users.getEmail()))) {
                        txtName.setText(users.getName());
                        txtUserEmail.setText(users.getEmail());
                        txtUserPassword.setText(users.getPassword());
                        txtUserAddress.setText(users.getAddress());
                        txtUserSchool.setText(users.getSchool());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void updateProfile() {
        String name = txtName.getText().toString();
        System.out.println(name);
        String email = txtUserEmail.getText().toString();
        String password = txtUserPassword.getText().toString();
        String school = txtUserSchool.getText().toString();
        String address = txtUserAddress.getText().toString();

        // get email from home page
        Intent prevIntent = getIntent();
        String userEmail = prevIntent.getStringExtra("emailLoggedIn");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    com.example.countyourcarbon00.Users users = snapshot.getValue(com.example.countyourcarbon00.Users.class);
                    // System.out.println(emailLoggedIn); Working
                    if (userEmail.equals((users.getEmail()))) {
                        HashMap<String, Object> userdataMap = new HashMap<>();
                        userdataMap.put("email", email);
                        userdataMap.put("password", password);
                        userdataMap.put("name", name);
                        userdataMap.put("school", school);
                        userdataMap.put("address", address);

                        reference.child(userEmail).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(UpdateProfile.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(UpdateProfile.this, com.example.countyourcarbon00.Profile.class);
                                    intent.putExtra("emailLoggedIn", userEmail);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        /*
        final DocumentReference sDoc = db.collection( "Users").document(currentUid);

        db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sDoc);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                transaction.update(sDoc, "name", name);
                transaction.update(sDoc, "email", email);
                transaction.update(sDoc, "password", password);
                transaction.update(sDoc, "school", school);
                transaction.update(sDoc, "address", address);

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(UpdateProfile.this, "updated", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       Toast.makeText(UpdateProfile.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });
         */
    }
}