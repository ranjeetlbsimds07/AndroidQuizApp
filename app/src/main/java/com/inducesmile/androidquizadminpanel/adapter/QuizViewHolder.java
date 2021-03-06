package com.inducesmile.androidquizadminpanel.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inducesmile.androidquizadminpanel.R;

public class QuizViewHolder extends RecyclerView.ViewHolder{

    public ImageView quizImage;
    public TextView quizName;
    public View view;

    public QuizViewHolder(View itemView) {
        super(itemView);
        quizImage = (ImageView)itemView.findViewById(R.id.selected_quiz_image);
        quizName = (TextView)itemView.findViewById(R.id.selected_quiz_name);
        view = itemView;
    }
}
