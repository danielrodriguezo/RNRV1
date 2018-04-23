package tk.rock_n_rolo.rocknrolov1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout layoutDot;
    private TextView[] dotstv;
    private int[] layouts;
    private Button btnSkip;
    private Button btnNext;
    private SliderAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!isFirstTimeStartApp()){
            startLoginActivity();
            finish();
        }
        setStatusBarTransparent();

        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        layoutDot = findViewById(R.id.dotLayout);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);

        //Cuando el usuario de click en "Omitir" se inicia el LoginActivity
        btnSkip.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startLoginActivity();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPage = viewPager.getCurrentItem()+1;
                if (currentPage<layouts.length){
                    //Avanzar a la siguiente página
                    viewPager.setCurrentItem(currentPage);
                } else{
                    startLoginActivity();
                }
            }
        });

        layouts = new int[]{R.layout.slider_1, R.layout.slider_2,R.layout.slider_3,R.layout.slider_4};
        pagerAdapter = new SliderAdapter(layouts,getApplicationContext());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == layouts.length-1){
                    //Última página
                    btnNext.setText("Comenzar");
                    btnSkip.setVisibility(View.GONE);
                }else{
                    btnNext.setText("Siguiente");
                    btnSkip.setVisibility(View.VISIBLE);
                }
                setDotStatus(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        }

        );
        setDotStatus(0);
    }

    private boolean isFirstTimeStartApp(){
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE);
        return ref.getBoolean("FirstTimeStartFlag", true);
    }

    private void setFirstTimeStartStatus(boolean stt){
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=ref.edit();
        editor.putBoolean("FirstTimeStartFlag", stt);
        editor.commit();
    }

    private void setDotStatus(int page){

        layoutDot.removeAllViews();
        dotstv = new TextView[layouts.length];
        for (int i = 0; i < dotstv.length; i++){
            dotstv[i] = new TextView(this);
            dotstv[i].setText(Html.fromHtml("&#8226"));
            dotstv[i].setTextSize(30);
            dotstv[i].setTextColor(Color.parseColor("#a9b4bb"));
            layoutDot.addView(dotstv[i]);
        }
        //Pone el color del punto en blanco
        if (dotstv.length>0){
            dotstv[page].setTextColor(Color.parseColor("#ffffff"));
        }

    }


    private void startLoginActivity(){

        setFirstTimeStartStatus(false);
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        finish();

    }


    private void setStatusBarTransparent(){

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}