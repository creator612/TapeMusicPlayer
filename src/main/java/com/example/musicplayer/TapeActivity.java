package com.example.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class TapeActivity extends AppCompatActivity {
    static int selectedTapeId = -1;
    static int selectedLabelId = -1;
    Button doneBtn;
    static ArrayList<DrawableFiles> tapesFiles = new ArrayList<>();
    static ArrayList<DrawableFiles> labelFiles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tape);
        Intent intent = getIntent();
        Field[] fields = R.drawable.class.getFields();
        if (tapesFiles.size() == 0 && labelFiles.size() == 0){
            for (Field field : fields) {
                if (field.getName().startsWith("tape_")) {
                    DrawableFiles df = new DrawableFiles(field.getName(), getResources().getIdentifier(field.getName(), "drawable", getPackageName()));
                    tapesFiles.add(df);
                }
                else if (field.getName().startsWith("label_")) {
                    DrawableFiles df = new DrawableFiles(field.getName(), getResources().getIdentifier(field.getName(), "drawable", getPackageName()));
                    labelFiles.add(df);
                }
            }
        }
        initViews();

        initViewPager();
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                intent.putExtra("tapeResourseId", selectedTapeId);
                intent.putExtra("labelResourseId", selectedLabelId);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void initViews() {
        doneBtn = findViewById(R.id.id_done);
    }

    private void initViewPager() {
        ViewPager viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TapeActivity.ViewPagerAdapter viewPagerAdapter = new TapeActivity.ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new TapeFragment(), "Tapes");
        viewPagerAdapter.addFragments(new LabelFragment(), "Labels");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;
        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        void addFragments(Fragment fragment, String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
