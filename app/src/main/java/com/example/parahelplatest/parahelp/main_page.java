package com.example.parahelplatest.parahelp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class main_page extends AppCompatActivity {
    private TextView tr_head,tl_head,br_head,bl_head,sentence;
    private RecyclerView tl_recyclerView,tr_recyclerView,br_recyclerView,bl_recyclerView;
    private ArtistsAdapter adapter_tl;
    private List<Artist> artistList_tl;
    private ArtistsAdapter adapter_tr;
    private List<Artist> artistList_tr;
    private ArtistsAdapter adapter_bl;
    private List<Artist> artistList_bl;
    private ArtistsAdapter adapter_br;
    private List<Artist> artistList_br;
    private String tr;
    private CardView tl_c;
    private String tl;
    private String br;
    private String bl;
    private CardView kal;
    private CardView kal2;
    private CardView kal3;
    public Button read;
    public Button backspace;


    TextToSpeech textToSpeech;



    DatabaseReference dbArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        tl_recyclerView = findViewById(R.id.tl_recycler);
        kal =findViewById(R.id.cardView3);
        kal2 =findViewById(R.id.cardView2);
        kal3=findViewById(R.id.cardView);
        tl_recyclerView.setHasFixedSize(true);
        tl_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        artistList_tl = new ArrayList<>();
        adapter_tl = new ArtistsAdapter(this, artistList_tl);
        tl_recyclerView.setAdapter(adapter_tl);
        getSupportActionBar().hide();




        tr_recyclerView = findViewById(R.id.tr_recycler);
        tr_recyclerView.setHasFixedSize(true);
        tr_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        artistList_tr = new ArrayList<>();
        adapter_tr = new ArtistsAdapter(this,artistList_tr);
        tr_recyclerView.setAdapter(adapter_tr);

        br_recyclerView = findViewById(R.id.br_recycler);
        br_recyclerView.setHasFixedSize(true);
        br_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        artistList_br = new ArrayList<>();
        adapter_br = new ArtistsAdapter(this, artistList_br);
        br_recyclerView.setAdapter(adapter_br);
        //read.findViewById(R.id.read);
        //backspace.findViewById(R.id.backspace);
        bl_recyclerView = findViewById(R.id.bl_recycler);
        bl_recyclerView.setHasFixedSize(true);
        bl_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        artistList_bl = new ArrayList<>();
        adapter_bl = new ArtistsAdapter(this, artistList_bl);
        bl_recyclerView.setAdapter(adapter_bl);


        tr_head=findViewById(R.id.tr);
        tl_head=findViewById(R.id.tl);
        br_head=findViewById(R.id.br);
        bl_head=findViewById(R.id.bl);
        sentence=findViewById(R.id.sentence);
        kal.getBackground().setAlpha(120);
        //kal2.getBackground().setAlpha(180);
        kal3.getBackground().setAlpha(0);

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


        //1. SELECT * FROM Artists
        dbArtists = FirebaseDatabase.getInstance().getReference("Words");


        //2. SELECT * FROM Artists WHERE id = "-LAJ7xKNj4UdBjaYr8Ju"


        Query nouns = FirebaseDatabase.getInstance().getReference("Words")
                .orderByChild("Type")
                .equalTo("Pronouns");

        Query verbs = FirebaseDatabase.getInstance().getReference("Words")
                .orderByChild("Type")
                .equalTo("Verbs");

        //4. SELECT * FROM Artists LIMIT 2
        Query query4 = FirebaseDatabase.getInstance().getReference("Artists").limitToFirst(2);


        //5. SELECT * FROM Artists WHERE age < 30
        Query query5 = FirebaseDatabase.getInstance().getReference("Artists")
                .orderByChild("age")
                .endAt(29);


        //6. SELECT * FROM Artists WHERE name = "A%"
        Query query6 = FirebaseDatabase.getInstance().getReference("Artists")
                .orderByChild("name")
                .startAt("A")
                .endAt("A\uf8ff");

        Reset();

        ;
        /*
         * You just need to attach the value event listener to read the values
         * for example
         * query6.addListenerForSingleValueEvent(valueEventListener)
         * */

        //article.addListenerForSingleValueEvent(valueEventListener_tl);

        //verbs.addListenerForSingleValueEvent(valueEventListener_bl);
        //bl="verb";

        //nouns.addListenerForSingleValueEvent(valueEventListener_br);
        //br="noun";

    }



    ValueEventListener valueEventListener_tr = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList_tr.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Artist artist = snapshot.getValue(Artist.class);
                    artistList_tr.add(artist);
                }
                adapter_tr.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    ValueEventListener valueEventListener_tl = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList_tl.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Artist artist = snapshot.getValue(Artist.class);
                    artistList_tl.add(artist);
                }
                adapter_tl.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    ValueEventListener valueEventListener_br = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList_br.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Artist artist = snapshot.getValue(Artist.class);
                    artistList_br.add(artist);
                }
                adapter_br.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    ValueEventListener valueEventListener_bl = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList_bl.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Artist artist = snapshot.getValue(Artist.class);
                    artistList_bl.add(artist);
                }
                adapter_bl.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public void bl_button(View view)
    {
        ColorDrawable[] colorDrawables = {new ColorDrawable(Color.GREEN),new ColorDrawable(Color.parseColor("#427B7D"))};
        TransitionDrawable transitionDrawable = new TransitionDrawable(colorDrawables);
        bl_head.setBackground(transitionDrawable);
        transitionDrawable.startTransition(2000);
        if(bl=="Verb")
        {
            Query verbspread1 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("subtype").equalTo("linking");
            verbspread1.addListenerForSingleValueEvent(valueEventListener_tl);
            tl="Linking Verbs";
            tl_head.setText(tl);

            Query questionspread2 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("subtype").equalTo("Action");
            questionspread2.addListenerForSingleValueEvent(valueEventListener_tr);
            tr="Action Verbs";
            tr_head.setText(tr);

            Query questionspread3 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("type").equalTo("clear");
            questionspread3.addListenerForSingleValueEvent(valueEventListener_bl);
            bl="";
            bl_head.setText(bl);

            Query questionspread4 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("type").equalTo("clear");
            questionspread4.addListenerForSingleValueEvent(valueEventListener_br);
            br="";
            br_head.setText(br);



        }
        else if(bl=="3")
        {
            sentence.append(" "+artistList_bl.get(0).getWord());
            Reset();


        }


    }
    public void br_button(View view)
    {
        ColorDrawable[] colorDrawables = {new ColorDrawable(Color.GREEN),new ColorDrawable(Color.parseColor("#427B7D"))};
        TransitionDrawable transitionDrawable = new TransitionDrawable(colorDrawables);
        br_head.setBackground(transitionDrawable);
        transitionDrawable.startTransition(2000);
        if (br == "Adjective") {
            Query questionspread1 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("17_adj");
            questionspread1.addListenerForSingleValueEvent(valueEventListener_tl);
            tl = "1";
            tl_head.setText(tl);

            Query questionspread2 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("18_adj");
            questionspread2.addListenerForSingleValueEvent(valueEventListener_tr);
            tr = "2";
            tr_head.setText(tr);

            Query questionspread3 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("type").equalTo("clear");
            questionspread3.addListenerForSingleValueEvent(valueEventListener_bl);
            bl = "";
            bl_head.setText(bl);

            Query questionspread4 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("type").equalTo("clear");
            questionspread4.addListenerForSingleValueEvent(valueEventListener_br);
            br = "";
            br_head.setText(br);


        }
        else if(br=="4")
        {
            sentence.append(" "+artistList_br.get(0).getWord());
            Reset();


        }

    }
    public void tl_button(View view) {

        ColorDrawable[] colorDrawables = {new ColorDrawable(Color.GREEN),new ColorDrawable(Color.parseColor("#427B7D"))};
        TransitionDrawable transitionDrawable = new TransitionDrawable(colorDrawables);
        tl_head.setBackground(transitionDrawable);
        transitionDrawable.startTransition(2000);



        if (tl == "Noun") {
            Query questionspread1 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("subtype").equalTo("pronoun");
            questionspread1.addListenerForSingleValueEvent(valueEventListener_tl);
            tl = "Pronoun";
            tl_head.setText(tl);

            Query questionspread2 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("subtype").equalTo("proper noun");
            questionspread2.addListenerForSingleValueEvent(valueEventListener_tr);
            tr = "Proper Noun";
            tr_head.setText(tr);

            Query questionspread3 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("type").equalTo("clear");
            questionspread3.addListenerForSingleValueEvent(valueEventListener_bl);
            bl = "";
            bl_head.setText(bl);

            Query questionspread4 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("type").equalTo("clear");
            questionspread4.addListenerForSingleValueEvent(valueEventListener_br);
            br = "";
            br_head.setText(br);




        } else if (tl == "1") {
            sentence.append(" " + artistList_tl.get(0).getWord());
            Reset();


        } else if (tl == "Pronoun") {
            Query questionspread3 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("5_pronoun");
            questionspread3.addListenerForSingleValueEvent(valueEventListener_tl);
            tl = "1";
            tl_head.setText(tl);

            Query questionspread4 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("6_pronoun");
            questionspread4.addListenerForSingleValueEvent(valueEventListener_tr);
            tr = "2";
            tr_head.setText(tr);

            Query questionspread5 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("7_pronoun");
            questionspread5.addListenerForSingleValueEvent(valueEventListener_bl);
            bl = "3";
            bl_head.setText(bl);

            Query questionspread6 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("");
            questionspread6.addListenerForSingleValueEvent(valueEventListener_br);
            br = "";
            br_head.setText(br);


        } else if (tl == "Linking Verbs") {
            Query questionspread3 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("13_linking");
            questionspread3.addListenerForSingleValueEvent(valueEventListener_tl);
            tl = "1";
            tl_head.setText(tl);

            Query questionspread4 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("14_linking");
            questionspread4.addListenerForSingleValueEvent(valueEventListener_tr);
            tr = "2";
            tr_head.setText(tr);

            Query questionspread5 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("type").equalTo("clear");
            questionspread5.addListenerForSingleValueEvent(valueEventListener_bl);
            bl = "";
            bl_head.setText(bl);

            Query questionspread6 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("type").equalTo("clear");
            questionspread6.addListenerForSingleValueEvent(valueEventListener_br);
            br = "";
            br_head.setText(br);
        }
    }


    public void tr_button(View view)
    {
        ColorDrawable[] colorDrawables = {new ColorDrawable(Color.GREEN),new ColorDrawable(Color.parseColor("#427B7D"))};
        TransitionDrawable transitionDrawable = new TransitionDrawable(colorDrawables);
        tr_head.setBackground(transitionDrawable);
        transitionDrawable.startTransition(2000);
        if(tr=="Question")
        {
            Query questionspread1 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("1_question");
            questionspread1.addListenerForSingleValueEvent(valueEventListener_tl);
            tl="1";
            tl_head.setText(tl);

            Query questionspread2 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("2_question");
            questionspread2.addListenerForSingleValueEvent(valueEventListener_tr);
            tr="2";
            tr_head.setText(tr);

            Query questionspread3 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("3_question");
            questionspread3.addListenerForSingleValueEvent(valueEventListener_bl);
            bl="3";
            bl_head.setText(bl);

            Query questionspread4 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("4_question");
            questionspread4.addListenerForSingleValueEvent(valueEventListener_br);
            br="4";
            br_head.setText(br);



        }
        else if(tr=="2")
        {
            sentence.append(" "+artistList_tr.get(0).getWord());
            Reset();


        }

        else if(tr=="linking")
        {
            Query questionspread3 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("10_linking");
            questionspread3.addListenerForSingleValueEvent(valueEventListener_tl);
            tl="1";
            tl_head.setText(tl);

            Query questionspread4 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("13_linking");
            questionspread4.addListenerForSingleValueEvent(valueEventListener_tr);
            tr="2";
            tr_head.setText(tr);

            Query questionspread5 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("14_linking");
            questionspread5.addListenerForSingleValueEvent(valueEventListener_bl);
            bl="3";
            bl_head.setText(bl);

            Query questionspread6 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("");
            questionspread6.addListenerForSingleValueEvent(valueEventListener_br);
            br="";
            br_head.setText(br);


        }
        else if(tr=="Proper Noun")
        {
            Query questionspread3 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("11_propernoun");
            questionspread3.addListenerForSingleValueEvent(valueEventListener_tl);
            tl="1";
            tl_head.setText(tl);

            Query questionspread4 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("12_propernoun");
            questionspread4.addListenerForSingleValueEvent(valueEventListener_tr);
            tr="2";
            tr_head.setText(tr);

            Query questionspread5 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("16_propernoun");
            questionspread5.addListenerForSingleValueEvent(valueEventListener_bl);
            bl="3";
            bl_head.setText(bl);

            Query questionspread6 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("type").equalTo("clear");
            questionspread6.addListenerForSingleValueEvent(valueEventListener_br);
            br="";
            br_head.setText(br);


        }
        else if(tr=="Action Verbs")
        {
            Query questionspread3 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("8_verb");
            questionspread3.addListenerForSingleValueEvent(valueEventListener_tl);
            tl="1";
            tl_head.setText(tl);

            Query questionspread4 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("9_verb");
            questionspread4.addListenerForSingleValueEvent(valueEventListener_tr);
            tr="2";
            tr_head.setText(tr);

            Query questionspread5 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("19_verb");
            questionspread5.addListenerForSingleValueEvent(valueEventListener_bl);
            bl="3";
            bl_head.setText(bl);

            Query questionspread6 = FirebaseDatabase.getInstance().getReference("Words").orderByChild("idr").equalTo("20_verb");
            questionspread6.addListenerForSingleValueEvent(valueEventListener_br);
            br="4";
            br_head.setText(br);


        }


    }
    public void Reseta(View view)
    {


        Reset();
        String s= sentence.getText().toString();
        String[] vals = s.split(" ");
        int n= wordcount(s);
        String g="";
        for (int i=0;i<n-1;i++)
        {
            g=g+vals[i];
        }
        sentence.setText(g);




    }
    static int wordcount(String string)
    {
        int count=0;

        char ch[]= new char[string.length()];
        for(int i=0;i<string.length();i++)
        {
            ch[i]= string.charAt(i);
            if( ((i>0)&&(ch[i]!=' ')&&(ch[i-1]==' ')) || ((ch[0]!=' ')&&(i==0)) )
                count++;
        }
        return count;
    }
    public void Reset()
    {
        Query question = FirebaseDatabase.getInstance().getReference("Words").orderByChild("type").equalTo("question");




        Query noun = FirebaseDatabase.getInstance().getReference("Words")
                .orderByChild("type")
                .equalTo("noun");

        question.addListenerForSingleValueEvent(valueEventListener_tr);
        tr="Question";
        tr_head.setText(tr);

        noun.addListenerForSingleValueEvent(valueEventListener_tl);
        tl="Noun";
        tl_head.setText(tl);


        Query verb = FirebaseDatabase.getInstance().getReference("Words")
                .orderByChild("type")
                .equalTo("verb");
        verb.addListenerForSingleValueEvent(valueEventListener_bl);
        bl="Verb";
        bl_head.setText(bl);

        Query adj = FirebaseDatabase.getInstance().getReference("Words")
                .orderByChild("type")
                .equalTo("adjective");

        adj.addListenerForSingleValueEvent(valueEventListener_br);
        br="Adjective";
        br_head.setText(br);
    }
    public void Read(View view)
    {

        textToSpeech.speak(sentence.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);




    }

}

