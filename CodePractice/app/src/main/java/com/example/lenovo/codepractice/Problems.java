package com.example.lenovo.codepractice;

import java.util.ArrayList;

/**
 * Created by Lenovo on 25-Mar-18.
 */

public class Problems {

    private static Problems objectProblems;
    ArrayList<Problem> solvedProblemsList;
    ArrayList<Problem> unsolvedProblemsList;
    ArrayList<Problem> problemsList;

    private Problems() {
        solvedProblemsList = new ArrayList<Problem>();
        unsolvedProblemsList = new ArrayList<Problem>();
        problemsList = new ArrayList<Problem>();
    }

    public static Problems getInstance(){
        if(objectProblems == null){
            objectProblems =  new Problems();
        }

        return objectProblems;
    }

    public static Problems getObjectProblems() {
        return objectProblems;
    }
}
