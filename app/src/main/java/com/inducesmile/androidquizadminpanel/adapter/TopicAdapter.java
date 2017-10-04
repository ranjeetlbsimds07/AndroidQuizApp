package com.inducesmile.androidquizadminpanel.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inducesmile.androidquizadminpanel.HelpActivity;
import com.inducesmile.androidquizadminpanel.QuizCategoryActivity;
import com.inducesmile.androidquizadminpanel.lightquiz.AppConstant;
import com.inducesmile.androidquizadminpanel.models.QuizObject;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicViewHolder>{

    private Context context;
    private List<QuizObject> quizList;
    private int layoutResource;

    public TopicAdapter(Context context, List<QuizObject> quizList, int layout) {
        this.context = context;
        this.quizList = quizList;
        this.layoutResource = layout;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int position) {
        final QuizObject quizObject = quizList.get(position);
        holder.quizName.setText(quizObject.getQuizName());
        //holder.quizImage.setImageResource(R.drawable.profile);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent profileIntent = new Intent(context, QuizCategoryActivity.class);
                Intent profileIntent = new Intent(context, HelpActivity.class);
                profileIntent.putExtra(AppConstant.CATEGORY,quizObject.getQuizName().toString());
                context.startActivity(profileIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }
}

