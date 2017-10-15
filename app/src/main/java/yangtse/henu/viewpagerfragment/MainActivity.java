package yangtse.henu.viewpagerfragment;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView pictureTextView,moviewTextView,musicTextView;
    private ViewPager mViewPager;
    private ImageView cursor;
    private int offset=0;
    private int position_one;
    private int position_two;
    private int bmpW;
    public int currIndex=0;
    private ArrayList<Fragment>fragmentArrayList;
    private FragmentManager fragmentManager;
    public Context context;
    public static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        InitTextView();
        InitImageView();
        InitFragment();
        InitViewPager();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onResume() {
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }
    private void InitTextView(){
        pictureTextView=(TextView)findViewById(R.id.picture_text);
        moviewTextView=(TextView)findViewById(R.id.movie_text);
        musicTextView=(TextView)findViewById(R.id.music_text);
        pictureTextView.setOnClickListener(new MyOnClickListener(0));
        moviewTextView.setOnClickListener(new MyOnClickListener(1));
        musicTextView.setOnClickListener(new MyOnClickListener(2));
    }
    private void InitViewPager(){
        mViewPager=(ViewPager)findViewById(R.id.vPager);
        mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager,fragmentArrayList));
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(0);
        resetTextViewTextColor();
        pictureTextView.setTextColor(ResourcesCompat.getColor(getResources(), R.color.main_top_tab_color_2,null));
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());

    }
    private void InitImageView(){
        cursor=(ImageView)findViewById(R.id.cursor);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW=dm.widthPixels;
        bmpW=(screenW/3);
        setBmpW(cursor,bmpW);
        offset=0;
        position_one=(int)(screenW/3.0);
        position_two=position_one*2;
    }
    private void InitFragment(){
        fragmentArrayList=new ArrayList<Fragment>();
        fragmentArrayList.add(new PictureFragment());
        fragmentArrayList.add(new MovieFragment());
        fragmentArrayList.add(new MusicFragment());
        fragmentManager=getSupportFragmentManager();
    }
    class MyOnClickListener implements View.OnClickListener{
        private int index=0;
        public MyOnClickListener(int i){
            index=i;
        }
        @Override
        public void onClick(View v) {
            mViewPager.setCurrentItem(index);
        }
    }
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageSelected(int position) {
            Animation animation=null;
            switch (position){
                case 0:
                    if(currIndex==1){
                        animation=new TranslateAnimation(position_one,0,0,0);
                        resetTextViewTextColor();
                        pictureTextView.setTextColor(ResourcesCompat.getColor(getResources(), R.color.main_top_tab_color_2,null));
                    }else if(currIndex==2){
                        animation=new TranslateAnimation(position_two,0,0,0);
                        resetTextViewTextColor();
                        pictureTextView.setTextColor(ResourcesCompat.getColor(getResources(), R.color.main_top_tab_color_2,null));
                    }
                    break;
                case 1:
                    if(currIndex==0){
                        animation=new TranslateAnimation(offset,position_one,0,0);
                        resetTextViewTextColor();
                        pictureTextView.setTextColor(ResourcesCompat.getColor(getResources(), R.color.main_top_tab_color_2,null));
                    }else if(currIndex==2){
                        animation=new TranslateAnimation(position_two,position_one,0,0);
                        resetTextViewTextColor();
                        pictureTextView.setTextColor(ResourcesCompat.getColor(getResources(), R.color.main_top_tab_color_2,null));
                    }
                    break;
                case 2:
                    if(currIndex==0){
                        animation=new TranslateAnimation(offset,position_two,0,0);
                        resetTextViewTextColor();
                        pictureTextView.setTextColor(ResourcesCompat.getColor(getResources(), R.color.main_top_tab_color_2,null));
                    }else if(currIndex==1){
                        animation=new TranslateAnimation(position_one,position_two,0,0);
                        resetTextViewTextColor();
                        pictureTextView.setTextColor(ResourcesCompat.getColor(getResources(), R.color.main_top_tab_color_2,null));
                    }
                    break;
            }
            currIndex=position;
            animation.setFillAfter(true);
            animation.setDuration(300);
            cursor.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    private void setBmpW(ImageView imageView,int mwidth){
        ViewGroup.LayoutParams para;
        para=imageView.getLayoutParams();
        para.width=mwidth;
        imageView.setLayoutParams(para);
    }
    private void resetTextViewTextColor(){
        pictureTextView.setTextColor(ResourcesCompat.getColor(getResources(), R.color.main_top_tab_color,null));
    }
}
