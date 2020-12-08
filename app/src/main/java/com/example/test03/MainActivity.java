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
    private myAdapter adapter;

    Button btnok;
    SQLiteDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VariablesSetter();
        InitializeArrayList();
        DBDisplay(arrHole, arrPar, arrScore);

        /**            Button Click and Implementation            */
        btnok.setOnClickListener(v -> {
            String holeNo;
            int parNo, score;
            int i;

            holeNo = inputHole.getText().toString();
            i = Integer.parseInt(holeNo) - 1;
            parNo = Integer.parseInt(inputPar.getText().toString());
            score = Integer.parseInt(inputScore.getText().toString());

            arrPar.set(i, parNo);
            arrScore.set(i, score);

            DBDisplay(arrHole, arrPar, arrScore);
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

    public void DBDisplay(ArrayList<String> holearr, ArrayList<Integer> pararr, ArrayList<Integer> scorearr) {

        PutArrayToDB(holearr, pararr, scorearr);

        adapter = new myAdapter(this, getAllItems());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    public void PutArrayToDB(ArrayList<String> holearr, ArrayList<Integer> pararr, ArrayList<Integer> scorearr) {
        myDBHelper.deleteAllData();

        int sumParOut = 0;
        int sumScoreOut1 = 0;
        for (int j = 0; j < 9; j++) {
            sumParOut = pararr.get(j) + sumParOut;
            sumScoreOut1 = scorearr.get(j) + sumScoreOut1;
        }
        pararr.set(9, sumParOut);
        scorearr.set(9, sumScoreOut1);

        int sumParIn = 0;
        int sumScoreIn1 = 0;
        for (int j = 10; j < 19; j++) {
            sumParIn = pararr.get(j) + sumParIn;
            sumScoreIn1 = scorearr.get(j) + sumScoreIn1;
        }
        pararr.set(19, sumParIn);
        scorearr.set(19, sumScoreIn1);

        pararr.set(20, sumParIn + sumParOut);
        scorearr.set(20, sumScoreIn1 + sumScoreOut1);

        for (int i = 0; i < holearr.size(); i++) {
            myDBHelper.saveToDB(String.valueOf(holearr.get(i)), pararr.get(i), scorearr.get(i));
        }
    }

    public void InitializeArrayList() {
        arrHole.clear();
        arrPar.clear();
        arrScore.clear();
        for (int i = 0; i < 20; i++) {
            arrHole.add(String.valueOf(i + 1));
            arrPar.add(4);
            arrScore.add(0);
        }

        arrHole.add(9, "OUT");
        arrPar.add(9, 4);
        arrScore.add(9, 0);

        arrHole.set(19, "IN");
        arrPar.set(19, 4);
        arrScore.set(19, 0);

        arrHole.set(20, "TTL");
        arrPar.set(20, 4);
        arrScore.set(20, 0);

    }

    public void VariablesSetter() {
        inputHole = findViewById(R.id.inputHole);
        inputPar = findViewById(R.id.inputPar);
        inputScore = findViewById(R.id.inputScore);
        btnok = findViewById(R.id.buttonOK);
        myDB = myDBHelper.getWritableDatabase();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new myAdapter(this, getAllItems());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    private Cursor getAllItems() {
        return myDB.query(TABLE_NAME, null, null, null,
                null, null, null);
    }

}