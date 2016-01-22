package com.zj.example.customview.funnel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Integer> moneys = new ArrayList<>();
        int x1 = 20000;
        moneys.add(x1);
        int x2 = 100000;
        moneys.add(x2);
        int x3 = 80000;
        moneys.add(x3);
        int x4 = 100000;
        moneys.add(x4);
        int x5 = 90000;
        moneys.add(x5);
        FunnelView funnelView = (FunnelView) findViewById(R.id.funnelview);



        funnelView.setData(moneys, x1 + x2 + x3 + x4 + x5);
        funnelView.animateY();
    }
}
