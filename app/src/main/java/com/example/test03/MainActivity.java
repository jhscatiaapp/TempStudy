package com.example.test03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String DATABASE_NAME = "score_test01.db";
    public static final String TABLE_NAME = "Score_Table";
    public static final String COL_DATE = "DATE";
    public static final String COL_CC = "CC";
    public static final String COL_PLAYER = "PLAYER_NAME";
    public static final String COL_HOLE = "HOLE";
    public static final String COL_PAR = "PAR";
    public static final String COL_SCORE = "SCORE";

    private ArrayList<String> arrHole = new ArrayList<>();
    private ArrayList<Integer> arrPar = new ArrayList<>();
    private ArrayList<Integer> arrScore = new ArrayList<>();
    private ArrayList<listDataFormat> allList = new ArrayList<>();

    private myDBHelper myDBHelper = new myDBHelper(this);

    private EditText inputHole, inputPar, inputScore;

    private RecyclerView recyclerView;
    //private ListView listView;
    //private myAdapter3 adapter;
    private myAdapter adapter;


    Button btnok;
    SQLiteDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VariablesSetter();
        InitializeArrayList();
        PutArrayToDB();
        DBDisplay();

        /**            Button Click and Implementation            */
        btnok.setOnClickListener(v -> {
            String holeNo;
            int parNo, score;
            int i;

            holeNo = inputHole.getText().toString();
            i = Integer.parseInt(holeNo) - 1;
            parNo = Integer.parseInt(inputPar.getText().toString());
            score = Integer.parseInt(inputScore.getText().toString());

            //arrHole.add(i, holeNo);
            arrPar.set(i, parNo);
            arrScore.set(i, score);

            //myDBHelper.deleteAllData();
            //PutArrayToDB();
            DBDisplay();
        });
        /**--------------------------------------------------------*/


        /**           Once click score board and implement modify dialog
         *            but is it not clickable. need to check and solve it           */
        adapter.setOnItemClickListener(new myAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "Clicked position " + position, Toast.LENGTH_SHORT).show();
            }
        });
        /**---------------------------------------------------------------------*/


        /**    implement OUT/IN and TTL score column to insert score board        */

    }

    public void DBDisplay() {
        String hole;
        int par, score;
        allList.clear();
        for (int i = 0; i < 18; i++) {
            hole = arrHole.get(i);
            par = arrPar.get(i);
            score = arrScore.get(i);
            listDataFormat tempList = new listDataFormat(hole, par, score);
            allList.add(tempList);
        }

        PutArrayToDB();

        adapter = new myAdapter(this, getAllItems());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    public void PutArrayToDB() {
        myDBHelper.deleteAllData();
        for(int i = 0; i < 18; i++) {
            myDBHelper.saveToDB(String.valueOf(arrHole.get(i)), arrPar.get(i), arrScore.get(i));
        }
    }

    public void InitializeArrayList() {
        arrHole.clear();
        arrPar.clear();
        arrScore.clear();
        for(int i = 0; i < 18; i++) {
            arrHole.add(String.valueOf(i+1));
            arrPar.add(4);
            arrScore.add(0);
        }
    }

    public void VariablesSetter() {
        inputHole = findViewById(R.id.inputHole);
        inputPar = findViewById(R.id.inputPar);
        inputScore = findViewById(R.id.inputScore);
        btnok = findViewById(R.id.buttonOK);
        myDB = myDBHelper.getWritableDatabase();
        recyclerView = findViewById(R.id.recyclerView);
        //listView = findViewById(R.id.listView);
        adapter = new myAdapter(this, getAllItems());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new myAdapter(this, getAllItems());
        recyclerView.setAdapter(adapter);

    }

    private Cursor getAllItems() {
        return myDB.query(TABLE_NAME, null, null, null,
                null, null, null);
    }

}