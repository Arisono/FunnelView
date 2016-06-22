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
        int x1 = 5000;
        moneys.add(x1);
        int x2 = 4000;
        moneys.add(x2);
        int x3 = 300;
        moneys.add(x3);
        int x4 = 8000;
        moneys.add(x4);
        int x5 = 9000;
        moneys.add(x5);
        int x6 =500;
        moneys.add(x6);
        int x7 = 7000;
        moneys.add(x7);
        int x8 = 6000;
        moneys.add(x8);
        int x9 = 3000;
        moneys.add(x9);
        int x10= 5000;
        moneys.add(x10);

        FunnelView funnelView = (FunnelView) findViewById(R.id.funnelview);



        funnelView.setData(moneys, x1 + x2 + x3 + x4 + x5+x6+x7+x8+x9+x10);
        funnelView.animateY();
    }
}
