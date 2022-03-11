package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//https://www.youtube.com/watch?v=XactTKR0Wfc

public class Profile extends AppCompatActivity implements View.OnClickListener {

    TextView profileName, profileEmail, profilePassword, profileSchool, profileAddress, profileCarbon;
    Button btnUpdateUser;
    ImageButton imageButtonEdit, imageButtonMenu;

    //https://www.youtube.com/watch?v=34MJSx3OPMg
    //retrieve user profile data from firestore -2 || Java coding || Social media app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = (TextView) findViewById(R.id.profileName);
        profileEmail = (TextView) findViewById(R.id.profileEmail);
        profilePassword = (TextView) findViewById(R.id.profilePassword);
        profileSchool = (TextView) findViewById(R.id.profileSchool);
        profileAddress = (TextView) findViewById(R.id.profileAddress);
        profileCarbon = (TextView) findViewById(R.id.profileCarbon);
        imageButtonMenu = (ImageButton) findViewById(R.id.imageButtonMenu);
        imageButtonEdit = (ImageButton) findViewById(R.id.imageButtonEdit);

    }

    @Override
    public void onStart() {
        super.onStart();
        /*
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            System.out.println("user is not there");
        }
        String currentUser = user.getUid();
        DocumentReference reference;
        FirebaseFirestore Firestore = FirebaseFirestore.getInstance();
        */
        // get email from home page
        Intent prevIntent = getIntent();
        String userEmail = prevIntent.getStringExtra("emailLoggedIn");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Users users = snapshot.getValue(Users.class);
                    // System.out.println(emailLoggedIn); Working
                    if (userEmail.equals((users.getEmail()))) {
                        String txt = users.getName() + " : " + users.getEmail() + " : " + users.getPassword() + " : " + users.getAddress() + " : " + users.getSchool() + " : " + users.getResult();
                        System.out.println(txt);

                        profileName.setText(users.getName());
                        profileEmail.setText(users.getEmail());
                        profilePassword.setText(users.getPassword());
                        profileAddress.setText(users.getAddress());
                        profileSchool.setText(users.getSchool());
                        profileCarbon.setText(String.format(users.getResult().toString()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        /*
        reference = Firestore.collection("user").document(currentUser);

        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.getResult().exists()) {

                            String nameResult = task.getResult().getString("name");
                            String emailResult = task.getResult().getString("email");
                            String passwordResult = task.getResult().getString("password");
                            String addressResult = task.getResult().getString("address");
                            String schoolResult = task.getResult().getString("school");

                            profileName.setText(nameResult);
                            profileEmail.setText(emailResult);
                            profilePassword.setText(passwordResult);
                            profileAddress.setText(addressResult);
                            profileSchool.setText(schoolResult);


                        } else {
                            Intent intent = new Intent(Profile.this, com.example.countyourcarbon00.UpdateProfile.class);
                            startActivity(intent);
                        }
                    }
                });
         */
        btnUpdateUser = (Button) findViewById(R.id.btnUpdateUser);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditUser();
            }
        });

        imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){ openEditUser();}
        });

        imageButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){ openUserHome();}
        });
    }

    public void openEditUser() {
        Intent intent = new Intent(this, UpdateProfile.class);
        Intent prevIntent = getIntent();
        String userEmail = prevIntent.getStringExtra("emailLoggedIn");
        intent.putExtra("emailLoggedIn", userEmail);
        startActivity(intent);
    }

    public void openUserHome() {
        Intent intent = new Intent(this, UserHomePage.class);
        Intent prevIntent = getIntent();
        String userEmail = prevIntent.getStringExtra("emailLoggedIn");
        intent.putExtra("emailLoggedIn", userEmail);
        startActivity(intent);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }

}




 /*   private Button logout;
    private EditText edit;
    private Button add;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent prevIntent = getIntent();
        String emailLoggedIn = prevIntent.getStringExtra("emailLoggedIn");
        System.out.println("The Email logged in is: " + emailLoggedIn);

        logout = findViewById(R.id.btnLogout);
        edit = findViewById(R.id.txtEnterName);
        add = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.lstUser);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Profile.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Profile.this , HomeScreen.class));
            }
        });

        logout.setOnClickListener(v); {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(Profile.this, "Logged Out!", Toast.LENGTH_SHORT).show());
            startActivity(new Intent(Profile.this , StartActivity.class));
        };

              add.setOnClickListener(new View.OnClickListener() {
              public void onClick(View v) {
              String txtEnterName = edit.getText().toString();
              if (txtEnterName.isEmpty()) {
                    Toast.makeText(Profile.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance().getReference().child("Users").push().child("Name").setValue(txtEnterName);
                }
            }
        });

        add.setOnClickListener(v) {
            String txtEnterName = edit.getText().toString();
            if (txtEnterName.isEmpty()) {
                Toast.makeText(Profile.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                //show();
            } else {
                FirebaseDatabase.getInstance().getReference().child("Users").push().child("Name").setValue(txtEnterName);
            }
        };


        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_user, list );
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    com.example.countyourcarbon00.Users users = snapshot.getValue(com.example.countyourcarbon00.Users.class);
                    // System.out.println(emailLoggedIn); Working
                 if (emailLoggedIn.equals((users.getEmail()))) {
                        String txt = users.getName() + " : " + users.getEmail() + " : " + users.getPassword() + " : " + users.getAddress() + " : " + users.getSchool() + " : " + users.getResult();
                        System.out.println(txt);
                        list.add(snapshot.getValue().toString());
                    }
                }
                adapter.notifyDataSetChanged();
            }
                @Override
            public void onCancelled(@NonNull DatabaseError databaseError){

                }
        });


        } */

