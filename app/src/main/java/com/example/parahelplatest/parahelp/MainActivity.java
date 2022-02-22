package com.example.parahelplatest.parahelp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RecyclerView tr_recyclerView,tl_recyclerView,br_recyclerView,bl_recyclerView;
    private TextView tr_head,tl_head,br_head,bl_head;
    TextToSpeech textToSpeech;


    recycleAdapter
            adapter; // Create Object of the Adapter class
    DatabaseReference mbase;
    Query dbques;// Create object of the
    // Firebase Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        // Create a instance of the database and get
        // its reference

        //1.select * from words
        mbase = FirebaseDatabase.getInstance().getReference();

        //2.select * from words where type = "Question";
        dbques = FirebaseDatabase.getInstance().getReference().orderByChild("Type").equalTo("Question");

        tr_head=findViewById(R.id.tr);
        tl_head=findViewById(R.id.tl);
        br_head=findViewById(R.id.br);
        bl_head=findViewById(R.id.bl);


        tr_recyclerView = findViewById(R.id.tr_recycler);

        // To display the Recycler view linearly
        tr_recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        tl_recyclerView = findViewById(R.id.tl_recycler);

        // To display the Recycler view linearly
        tl_recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        br_recyclerView = findViewById(R.id.br_recycler);

        // To display the Recycler view linearly
        br_recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        bl_recyclerView = findViewById(R.id.bl_recycler);

        // To display the Recycler view linearly
        bl_recyclerView.setLayoutManager(
                new LinearLayoutManager(this));


        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<person> options
                = new FirebaseRecyclerOptions.Builder<person>()
                .setQuery(mbase, person.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new recycleAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        tr_recyclerView.setAdapter(adapter);
        tl_recyclerView.setAdapter(adapter);
        br_recyclerView.setAdapter(adapter);
        bl_recyclerView.setAdapter(adapter);
    }
    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}

