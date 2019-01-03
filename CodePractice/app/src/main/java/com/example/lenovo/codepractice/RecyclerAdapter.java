package com.example.lenovo.codepractice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Lenovo on 25-Mar-18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<ProblemHolder> {
    int type;

    private static RecyclerAdapter obj_1;
    private static RecyclerAdapter obj_2;

    private RecyclerAdapter(int type) {
        this.type = type;
    }

    public static RecyclerAdapter getInstance_1(){
        if(obj_1 == null){
            obj_1 = new RecyclerAdapter(1);
        }
        return obj_1;
    }

    public static RecyclerAdapter getInstance_2(){
        if(obj_2 == null){
            obj_2 = new RecyclerAdapter(2);
        }
        return obj_2;
    }

    @Override
    public ProblemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_layout,parent,false);
        return new ProblemHolder(v, type);
    }

    @Override
    public void onBindViewHolder(ProblemHolder holder, int position) {
        Problems prb = Problems.getObjectProblems();
        if (this.type == 1) {
            if (prb.solvedProblemsList != null) {
                Problem problem = prb.solvedProblemsList.get(position);
                holder.bindRow(problem);
            }
        } else {
            if (prb.unsolvedProblemsList != null) {
                Problem problem = prb.unsolvedProblemsList.get(position);
                holder.bindRow(problem);
            }
        }
    }

    @Override
    public int getItemCount() {
        Problems prb = Problems.getObjectProblems();
        if(this.type == 1)
            return prb.solvedProblemsList.size();
        else
            return prb.unsolvedProblemsList.size();
    }
}
