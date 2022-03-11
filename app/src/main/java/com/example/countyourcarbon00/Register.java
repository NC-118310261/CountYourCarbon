package com.example.countyourcarbon00;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

//https://firebase.google.com/docs/auth/android/start


//https://firebase.google.com/docs/database/android/start
//https://github.com/firebase/snippets-android/blob/8184cba2c40842a180f91dcfb4a216e721cc6ae6/database/app/src/main/java/com/google/firebase/referencecode/database/MainActivity.java#L40-L44
// implements View.onClickListener
public class Register extends AppCompatActivity {
//Register method from youtube video
    //https://www.youtube.com/watch?v=Z-RE1QuUWPg&t=847s

    EditText txtName, txtUserEmail, txtUserPassword, txtUserSchool, txtUserAddress;
    String currentUserId;
    Button btnRegisterUser;
    StorageReference storageReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    Users users;
    private FirebaseAuth mAuth;
    double resultValue; // global bar
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent prevIntent = getIntent();
        resultValue = prevIntent.getDoubleExtra("resultValue", 0.0);
        System.out.println("Users total carbon footprint " + resultValue);

        mAuth = FirebaseAuth.getInstance();

        //textViewRegister = (TextView) findViewById(R.id.banner);
        //textViewRegister.setOnClickListener(this);

        txtName = (EditText) findViewById(R.id.txtName);
        txtUserEmail = (EditText) findViewById(R.id.txtUserEmail);
        txtUserPassword = (EditText) findViewById(R.id.txtUserPassword);
        txtUserSchool = (EditText) findViewById(R.id.txtUserSchool);
        txtUserAddress = (EditText) findViewById(R.id.txtUserAddress);
        loadingBar = new ProgressDialog(this);
        btnRegisterUser = (Button) findViewById(R.id.btnRegisterUser);

        /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        currentUserId = user.getEmail();*/
        /*
        documentReference = db.collection("user").document(currentUserId);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = database.getReference("Users"); */

        storageReference = FirebaseStorage.getInstance().getReference();

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v){ CreateAccount(); }
         /*   if (v.getId() == R.id.btnRegisterUser) {
                register(); */
            });
        }

    private void CreateAccount(){
        String name = txtName.getText().toString();
        String email = txtUserEmail.getText().toString();
        String password = txtUserPassword.getText().toString();
        String school = txtUserSchool.getText().toString();
        String address = txtUserAddress.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your phone email...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(school))
        {
            Toast.makeText(this, "Please write your school of study...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(address))
        {
            Toast.makeText(this, "Please write your address...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            ValidateEmail(name, email, password, school, currentUserId, address);

            final StorageReference reference = storageReference.child(System.currentTimeMillis()+ ".");
        }

    }

    private void ValidateEmail(final String name, final String email,final String password, final String school, final String currentUserId, final String address) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(email).exists())){
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("email", email);
                    userdataMap.put("password", password);
                    userdataMap.put("name", name);
                    userdataMap.put("school", school);
                    userdataMap.put("address", address);
                    userdataMap.put("uid" , currentUserId);
                    userdataMap.put("result" , resultValue);
                    RootRef.child("Users").child(email).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                Toast.makeText(Register.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(Register.this, com.example.countyourcarbon00.Login.class);
                                startActivity(intent);
                            }
                            else
                            {
                                loadingBar.dismiss();
                                Toast.makeText(Register.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }
                else {
                    Toast.makeText(Register.this, "This " + email + " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(Register.this, "Please try again using another email.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, com.example.countyourcarbon00.HomeScreen.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

   /* private void  register() {
        String name = txtName.getText().toString().trim();
        String email = txtUserEmail.getText().toString().trim();
        String password = txtUserPassword.getText().toString().trim();
        String school = txtUserSchool.getText().toString().trim();
        String address = txtUserAddress.getText().toString().trim();
        // resultValue is ready
        if(name.isEmpty()){
            txtName.setError("");
            txtName.requestFocus();
            return;
        }
        if(email.isEmpty()) {
            txtUserEmail.setError("");
            txtUserEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            txtUserPassword.setError("");
            txtUserPassword.requestFocus();
            return;
        }
        if(school.isEmpty()){
            txtUserSchool.setError("");
            txtUserSchool.requestFocus();
            return;
        }
        if(address.isEmpty()){
            txtUserAddress.setError("");
            txtUserAddress.requestFocus();
            return;
        } */

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Users users = new Users(name, email, password, school, address, resultValue);
                            FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>(){
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        //if (task.isSuccessful()) {
                                            System.out.println("User is registered");
                                            Intent intent = new Intent(Register.this, Profile.class);
                                            intent.putExtra("emailLoggedIn", email);
                                            startActivity(intent);
                                        //}
                                    }
                                });
                        }
                    }
                });
    }
}