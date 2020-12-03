package com.example.test03;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public static final String COL_HOLE = "HOLE";
    public static final String COL_PAR = "PAR";
    public static final String COL_SCORE = "SCORE";

    private OnItemClickListener mListener;

    /** to make interface for clickable adapter */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public myAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_listview, parent, false);
        return new myViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        String strHole = mCursor.getString(mCursor.getColumnIndex(COL_HOLE));
        int intPar = mCursor.getInt(mCursor.getColumnIndex(COL_PAR));
        int intScore = mCursor.getInt(mCursor.getColumnIndex(COL_SCORE));

        holder.holeView.setText(strHole);
        holder.parView.setText(String.valueOf(intPar));
        holder.scoreView.setText(String.valueOf(intScore));

        long id = mCursor.getLong(mCursor.getColumnIndex(COL_SCORE));
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView holeView;
        public TextView parView;
        public TextView scoreView;

        public myViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            holeView = itemView.findViewById(R.id.hole_no);
            parView = itemView.findViewById(R.id.par_no);
            scoreView = itemView.findViewById(R.id.player_score);

            /** Make onClickListener RecyclerView  */
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

}
