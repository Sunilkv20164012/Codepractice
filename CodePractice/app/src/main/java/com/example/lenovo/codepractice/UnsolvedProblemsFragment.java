package com.example.lenovo.codepractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lenovo on 26-Mar-18.
 */

public class UnsolvedProblemsFragment extends android.support.v4.app.Fragment {

    RecyclerView unSolvedProblemRecyclerView;
    RecyclerAdapter adapter = RecyclerAdapter.getInstance_2();
    LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_solved_problems,container,false);
        unSolvedProblemRecyclerView = v.findViewById(R.id.recView_sp);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        unSolvedProblemRecyclerView.setLayoutManager(linearLayoutManager);
        unSolvedProblemRecyclerView.setAdapter(adapter);
    }
}
