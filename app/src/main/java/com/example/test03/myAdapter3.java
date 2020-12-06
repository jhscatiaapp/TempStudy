package com.example.test03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class myAdapter3 extends ArrayAdapter<listDataFormat> {

    private Context mContext;
    private String mHole;
    private int mPar, mScore;
    private TextView holeView, parView, scoreView;

    public myAdapter3(@NonNull Context context, int resource, @NonNull ArrayList<listDataFormat> objects) {
        super(context, resource, objects);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        mHole = getItem(position).getmHole();
        mPar = getItem(position).getmPar();
        mScore = getItem(position).getmScore();

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.layout_listview2, parent, false);

        holeView = view.findViewById(R.id.hole_no);
        parView = view.findViewById(R.id.par_no);
        scoreView = view.findViewById(R.id.player_score);

        holeView.setText(mHole);
        parView.setText(mPar);
        scoreView.setText(mScore);

        return view;
    }
}
