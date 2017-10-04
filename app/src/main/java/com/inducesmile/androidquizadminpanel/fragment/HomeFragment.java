package com.inducesmile.androidquizadminpanel.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.inducesmile.androidquizadminpanel.HomeActivity;
import com.inducesmile.androidquizadminpanel.R;
import com.inducesmile.androidquizadminpanel.adapter.QuizAdapter;
import com.inducesmile.androidquizadminpanel.models.QuizObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    private static final String TAG = HomeFragment.class.getSimpleName();

    private TextView playerName, playerStatus, playerScore;

    private Button quizStartButton;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Home");

        playerName = (TextView)view.findViewById(R.id.player_name);
        playerStatus = (TextView)view.findViewById(R.id.player_status);
        playerScore = (TextView)view.findViewById(R.id.player_score);
        if(!TextUtils.isEmpty(HomeActivity.userEmail)) {
            playerName.setText(HomeActivity.userEmail);
        }else{
            playerName.setText("");
        }
        RecyclerView selectedQuizRecyclerView = (RecyclerView)view.findViewById(R.id.selected_quizzes);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        selectedQuizRecyclerView.setLayoutManager(gridLayoutManager);
        selectedQuizRecyclerView.setHasFixedSize(true);

        QuizAdapter mAdapter = new QuizAdapter(getActivity(), getTestData(), R.layout.selected_quiz_layout);
        selectedQuizRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public List<QuizObject> getTestData() {
        List<QuizObject> testData = new ArrayList<>();
        testData.add(new QuizObject("", "General Knowledge"));
        testData.add(new QuizObject("", "Entertainment"));
        testData.add(new QuizObject("", "History"));
        testData.add(new QuizObject("", "Sports"));
        return testData;
    }
}
