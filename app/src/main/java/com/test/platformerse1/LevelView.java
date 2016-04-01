package com.test.platformerse1;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

// An unused class that I still haven't figured out how to safely delete. Please ignore.


public class LevelView extends Fragment {
    Environment environment;
    boolean started = false;
    boolean running = false;

    public LevelView() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        environment = Environment.getInstance();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level_view, container, false);
    }

    public void initLevel(int i){
        environment.initialize(LevelVault.levelOne(), Environment.player);
        started = true;
        initView();
        running = true;
    }

    public void initView() {
        for (int i = 0; i < environment.getBlocks().size(); ++i) {
//            ImageView imageView = new ImageView(LevelActivity.this);
//            imageView.setImageResource(R.drawable.block);
//            RelativeLayout r1 = (RelativeLayout) findViewById

        }

    }
}
