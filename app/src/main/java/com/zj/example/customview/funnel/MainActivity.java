package com.zj.example.customview.funnel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    FunnelTwoView funnelView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        funnelView = (FunnelTwoView) findViewById(R.id.funnelview2);
        onClickFunnel2(11);
        //onClickFunnelOne();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_funnel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.f1:
                funnelView.setAngleScale(1.85f);
                funnelView.requestLayout();
                funnelView.animateY();
                break;
            case R.id.f2:
                funnelView.setAngleScale(2f);
                funnelView.requestLayout();
                funnelView.animateY();
                break;
            case R.id.f3:
                onClickFunnel2(5);
                break;
            case R.id.f4:
                onClickFunnel2(10);
                break;
            case R.id.f5:
                onClickFunnel2(15);
                break;
            case R.id.f6:
                onClickFunnel2(30);
                break;
            case R.id.f7:
                onClickFunnel2(50);
                break;
            case R.id.f8:
                onClickFunnel2(65);
                break;
            case R.id.f9:
                onClickFunnel2(80);
                break;
            case R.id.f10:
                onClickFunnel2(100);
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
    private void onClickFunnel2(int total) {
        ArrayList<Integer> counts = new ArrayList<>();
        ArrayList<String> colors = new ArrayList<>();
        int countToatal=0;
        for(int i=0;i<total;i++){
           int itemCount=(int)(Math.random()*100+1);
           counts.add(itemCount);
           colors.add("#"+getRandColorCode());
           countToatal=countToatal+itemCount;
       }
     
        funnelView.setData(counts, countToatal, colors);
        funnelView.animateY();
//       new Handler().postDelayed(new Runnable() {
//           @Override
//           public void run() {
        Runtime.getRuntime().gc();
//           }
//       },3000);
    }

    private void onClickFunnelOne() {
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
//        FunnelView funnelView = (FunnelView) findViewById(R.id.funnelview);
//
//        funnelView.setData(moneys, x1 + x2 + x3 + x4 + x5+x6+x7+x8+x9+x10);
//        funnelView.animateY();
    }


    /**
     * 获取十六进制的颜色代码.例如 "#6E36B4" , For HTML ,
     *
     * @return String
     */
    public static String getRandColorCode() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return r + g + b;
    }
}
