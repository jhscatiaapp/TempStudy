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
    private myDBHelper myDBHelper = new myDBHelper(this);
    private EditText inputHole, inputPar, inputScore;
    private RecyclerView recyclerView;
    private myAdapter adapter;
    String holeNo;
    int parNo, score;
    Button btnok;
    SQLiteDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VariablesSetter();

        /*arrHole.add("1");
        arrHole.add("2");
        arrHole.add("3");
        arrPar.add(3);
        arrPar.add(4);
        arrPar.add(5);
        arrScore.add(0);
        arrScore.add(-1);
        arrScore.add(1);*/

        btnok.setOnClickListener(v -> {
            holeNo = inputHole.getText().toString();
            parNo = Integer.parseInt(inputPar.getText().toString());
            score = Integer.parseInt(inputScore.getText().toString());

            myDBHelper.saveToDB(holeNo, parNo, score);
        });

    }

    public void VariablesSetter() {
        inputHole = findViewById(R.id.inputHole);
        inputPar = findViewById(R.id.inputPar);
        inputScore = findViewById(R.id.inputScore);
        btnok = findViewById(R.id.buttonOK);
        myDB = myDBHelper.getWritableDatabase();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new myAdapter(this, getAllItems());
        recyclerView.setAdapter(adapter);
    }

    private Cursor getAllItems() {
        return myDB.query(TABLE_NAME, null, null, null,
                null, null, null);
    }

    private void removeItem(long hole) {
        myDB.delete(TABLE_NAME, COL_HOLE + "=" + hole, null);
        adapter.swapCursor(getAllItems());
    }

}