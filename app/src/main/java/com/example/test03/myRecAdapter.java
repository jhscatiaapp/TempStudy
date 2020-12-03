package com.example.test03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class myRecAdapter extends RecyclerView.Adapter<myRecAdapter.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mHoleView, mParView, mScoreView;

        public ViewHolder(@NonNull View itemView, TextView mHoleView, TextView mParView, TextView mScoreView)  {
            super(itemView);
            mHoleView = itemView.findViewById(R.id.hole_no);
            mParView = itemView.findViewById(R.id.par_no);
            mScoreView = itemView.findViewById(R.id.player_score);
        }
    }
}
