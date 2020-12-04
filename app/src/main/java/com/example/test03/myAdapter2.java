package com.example.test03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter2 extends RecyclerView.Adapter<myAdapter2.ViewHolder> {

    private ArrayList<String> arrHoleNo = new ArrayList<>();
    private ArrayList<Integer> arrParNo = new ArrayList<>();
    private ArrayList<Integer> arrScore = new ArrayList<>();
    private Context mContext;

    public myAdapter2(Context mContext, ArrayList<String> arrHoleNo, ArrayList<Integer> arrParNo, ArrayList<Integer> arrScore) {
        this.arrHoleNo = arrHoleNo;
        this.arrParNo = arrParNo;
        this.arrScore = arrScore;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.holeTextView.setText(arrHoleNo.get(position));
        holder.parTextView.setText(arrParNo.get(position));
        holder.scoreTextView.setText(arrScore.get(position));

        /**     필요시 onClickListener 추가     */

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView holeTextView, parTextView, scoreTextView;

        public ViewHolder (View itemView) {
            super(itemView);
            holeTextView = itemView.findViewById(R.id.hole_no);
            parTextView = itemView.findViewById(R.id.par_no);
            scoreTextView = itemView.findViewById(R.id.player_score);
        }


    }
}
