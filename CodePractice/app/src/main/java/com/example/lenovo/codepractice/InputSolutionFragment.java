package com.example.lenovo.codepractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Lenovo on 28-Mar-18.
 */

public class InputSolutionFragment extends Fragment {

    EditText solution_text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.input_solution_fragment,container,false);
        solution_text = v.findViewById(R.id.solution_et);
        return v;
    }
}
