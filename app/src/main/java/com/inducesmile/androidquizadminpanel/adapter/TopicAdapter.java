package com.inducesmile.androidquizadminpanel.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inducesmile.androidquizadminpanel.HelpActivity;
import com.inducesmile.androidquizadminpanel.HomeActivity;
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
    public void onBindViewHolder(final TopicViewHolder holder, int position) {
        final QuizObject quizObject = quizList.get(position);
        holder.quizName.setText(quizObject.getQuizName());
        //holder.quizImage.setImageResource(R.drawable.profile);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent profileIntent = new Intent(context, QuizCategoryActivity.class);
                /*if((HomeActivity.generalKnowledge ||
                        HomeActivity.entertainment ||
                        HomeActivity.history ||
                        HomeActivity.sports ||
                        HomeActivity.businessts ||
                        HomeActivity.computer)
                        && quizObject.getQuizName().toString().equalsIgnoreCase(holder.quizName.getText().toString())){

                    Intent profileIntent = new Intent(context, HelpActivity.class);
                    profileIntent.putExtra(AppConstant.CATEGORY,quizObject.getQuizName().toString());
                    context.startActivity(profileIntent);

                }else{
                    Toast.makeText(context,"You have already attempt to "+quizObject.getQuizName().toString(),Toast.LENGTH_LONG).show();
                }*/


                /*if(HomeActivity.generalKnowledge && quizObject.getQuizName().toString().equalsIgnoreCase("General Knowledge")){
                    Intent profileIntent = new Intent(context, HelpActivity.class);
                    profileIntent.putExtra(AppConstant.CATEGORY,quizObject.getQuizName().toString());
                    context.startActivity(profileIntent);
                }else if(HomeActivity.entertainment && quizObject.getQuizName().toString().equalsIgnoreCase("Entertainment")){
                    Intent profileIntent = new Intent(context, HelpActivity.class);
                    profileIntent.putExtra(AppConstant.CATEGORY,quizObject.getQuizName().toString());
                    context.startActivity(profileIntent);
                }else if(HomeActivity.history && quizObject.getQuizName().toString().equalsIgnoreCase("History")){
                    Intent profileIntent = new Intent(context, HelpActivity.class);
                    profileIntent.putExtra(AppConstant.CATEGORY,quizObject.getQuizName().toString());
                    context.startActivity(profileIntent);
                }else if(HomeActivity.sports && quizObject.getQuizName().toString().equalsIgnoreCase("Sports")){
                    Intent profileIntent = new Intent(context, HelpActivity.class);
                    profileIntent.putExtra(AppConstant.CATEGORY,quizObject.getQuizName().toString());
                    context.startActivity(profileIntent);
                }else if(HomeActivity.computer && quizObject.getQuizName().toString().equalsIgnoreCase("Computer")){
                    Intent profileIntent = new Intent(context, HelpActivity.class);
                    profileIntent.putExtra(AppConstant.CATEGORY,quizObject.getQuizName().toString());
                    context.startActivity(profileIntent);
                }else if(HomeActivity.businessts && quizObject.getQuizName().toString().equalsIgnoreCase("Business")){
                    Intent profileIntent = new Intent(context, HelpActivity.class);
                    profileIntent.putExtra(AppConstant.CATEGORY,quizObject.getQuizName().toString());
                    context.startActivity(profileIntent);
                }else{
                    Toast.makeText(context,"You have already attempt to "+quizObject.getQuizName().toString(),Toast.LENGTH_LONG).show();
                }*/

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

