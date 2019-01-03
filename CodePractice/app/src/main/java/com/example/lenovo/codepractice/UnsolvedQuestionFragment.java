package com.example.lenovo.codepractice;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Lenovo on 28-Mar-18.
 */

public class UnsolvedQuestionFragment extends Fragment {

    Button sumbit_button;
    TextView question_view;

    interface SubmitQuestion extends Parcelable{
        void onSubmitClicked();
    }

    SubmitQuestion callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /*if(savedInstanceState != null)
            callback = savedInstanceState.getParcelable("Callback");*/
        callback = getArguments().getParcelable("Callback");

        View v = inflater.inflate(R.layout.fragment_unsolved_question,container,false);
        question_view = v.findViewById(R.id.problem_tv);
        sumbit_button = v.findViewById(R.id.submit_bt);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sumbit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSubmitClicked();
            }
        });
    }
}
