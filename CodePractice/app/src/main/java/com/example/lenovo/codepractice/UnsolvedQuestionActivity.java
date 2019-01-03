package com.example.lenovo.codepractice;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lenovo on 28-Mar-18.
 */

public class UnsolvedQuestionActivity extends AppCompatActivity {

    UnsolvedQuestionFragment.SubmitQuestion call = new UnsolvedQuestionFragment.SubmitQuestion() {
        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {

        }

        @Override
        public void onSubmitClicked() {
            InputSolutionFragment inputSolutionFragment = new InputSolutionFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout,inputSolutionFragment)
                    .addToBackStack(null)
                    .commit();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsolved_question);

        if(findViewById(R.id.frame_layout) != null) {

            if (savedInstanceState != null)
                return;

            UnsolvedQuestionFragment unsolvedQuestionFragment = new UnsolvedQuestionFragment();

            Bundle args = new Bundle();
            args.putParcelable("Callback",call);
            unsolvedQuestionFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout,unsolvedQuestionFragment)
                    .commit();
        }
    }
}
