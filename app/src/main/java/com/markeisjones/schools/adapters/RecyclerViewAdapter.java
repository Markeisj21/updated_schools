package com.markeisjones.schools.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.markeisjones.schools.model.School_Info;

import java.util.List;
import com.markeisjones.schools.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<School_Info> mData ;

    public RecyclerViewAdapter(Context mContext, List list){

        this.mContext = mContext;
        this.mData = list;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater =LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.schoolinfo_row_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.dbn.setText(mData.get(position).getDbn());
        holder.testers.setText(mData.get(position).getNum_of_sat_test_takers());

        holder.math.setText(mData.get(position).getSat_math_avg_score());
        holder.reading.setText(mData.get(position).getSat_critical_reading_avg_score());
        holder.writing.setText(mData.get(position).getSat_writing_avg_score());
        holder.school.setText(mData.get(position).getSchool_name());




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dbn;
        TextView testers;
        TextView math;
        TextView writing;
        TextView reading;
        TextView school;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            dbn = itemView.findViewById(R.id.dbn);
            testers = itemView.findViewById(R.id.num_of_takers);
            reading = itemView.findViewById(R.id.reading_score);
            math = itemView.findViewById(R.id.math_score);
            writing = itemView.findViewById(R.id.writting_score);
            school = itemView.findViewById(R.id.school_name);
        }
    }
}
