package com.inducesmile.androidquizadminpanel.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inducesmile.androidquizadminpanel.HomeActivity;
import com.inducesmile.androidquizadminpanel.R;
import com.inducesmile.androidquizadminpanel.adapter.LadderBoardPagerAdapter;
import com.inducesmile.androidquizadminpanel.lightquiz.GameOver;

public class LadderBoardFragment extends Fragment {


    public LadderBoardFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ladder_board, container, false);
        getActivity().setTitle("Score Board");

        TextView text = (TextView) view.findViewById(R.id.text);
        if(HomeActivity.userEmail.equalsIgnoreCase("")){
            text.setText("To check your score please create login.");
        }else {
            text.setText("Your Score:" + GameOver.Getscore);
        }
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("All Time Top Score"));
        tabLayout.addTab(tabLayout.newTab().setText("Daily Top Score"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        final LadderBoardPagerAdapter adapter = new LadderBoardPagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}
