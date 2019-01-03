package com.example.lenovo.codepractice;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Lenovo on 25-Mar-18.
 */

public class ProblemHolder extends RecyclerView.ViewHolder {

    int problemType;
    View v;
    TextView textView;

    public ProblemHolder(View itemView, int type) {
        super(itemView);
        v = itemView;
        this.problemType = type;
    }

    public void bindRow(final Problem problem){
        textView = v.findViewById(R.id.row_item);
        textView.setText(problem.getTitle());

        if(problemType == 2){
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(v.getContext(),UnsolvedQuestionActivity.class);
                    i.putExtra("PROBLEM",problem);
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
