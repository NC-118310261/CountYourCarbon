package com.example.countyourcarbon00;
//https://www.youtube.com/watch?v=M8sKwoVjqU0
//Firebase Data to RecyclerView | How to Retrieve Firebase Data into Recyclerview | Android Studio

import android.os.Bundle;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<Users> list = new ArrayList<Users>();
    private SearchView searchView;
    private String currentSearchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Users>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        //setupData();
        searchView = findViewById(R.id.userDetailsSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                currentSearchText = s;
                ArrayList<Users> filteredList = new ArrayList<Users>();

                for (Users users : list) {
                    if (users.getName().toLowerCase().contains(currentSearchText.toLowerCase())) {
                        filteredList.add(users);
                    } // have spinner - for searching term
                }
                for (Users users : list) {
                    if (users.getEmail().toLowerCase().contains(currentSearchText.toLowerCase())) {
                        filteredList.add(users);
                    }
                }
                for (Users users : list) {
                    if (users.getPassword().toLowerCase().contains(currentSearchText.toLowerCase())) {
                        filteredList.add(users);
                    }
                }
                for (Users users : list) {
                    if (users.getSchool().toLowerCase().contains(currentSearchText.toLowerCase())) {
                        filteredList.add(users);
                    }
                }
                for (Users users : list) {
                    if (users.getAddress().toLowerCase().contains(currentSearchText.toLowerCase())) {
                        filteredList.add(users);
                    }
                }
                MyAdapter adapter = new MyAdapter(getApplicationContext(), filteredList);
                recyclerView.setAdapter(adapter);
                return false;
            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Users user = dataSnapshot.getValue(Users.class);
                    list.add(user);

                }
                myAdapter.notifyDataSetChanged();

            }

            /*
             * This method will be triggered in the event that this listener either failed at the server, or
             * is removed as a result of the security and Firebase Database rules. For more information on
             * securing your data, see: <a
             * href="https://firebase.google.com/docs/database/security/quickstart" target="_blank"> Security
             * Quickstart</a>
             *
             * @param error A description of the error that occurred
             */
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
 /*   private void initSearchWidgets() {
        searchView = (SearchView) findViewById(R.id.userListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                currentSearchText = s;
                ArrayList<Users> filteredList = new ArrayList<Users>();

                for (Users users : filteredList) {
                    if (users.getName().toLowerCase().contains(s.toLowerCase())) {
                        if (selectedFilter.equals("all")) {
                            filteredList.add(users);
                        } else {
                            if (users.getName().toLowerCase().contains(selectedFilter)) {
                                //           selectedFilter.add(survey);
                            }
                        }
                    }
                }
                //       SurveyAdapter adapter = new SurveyAdapter(getApplicationContext(), 0, selectedFilter);
                //      recyclerView.setAdapter(adapter);

                return false;
            }
        });
    }*/
}