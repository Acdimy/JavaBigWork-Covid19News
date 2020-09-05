package news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.uitest.R;

import java.util.ArrayList;
import java.util.List;


public class newsFragment extends Fragment {
    private TabLayout newsTabLayout;
    private ViewPager newsViewPaper;
    private String searchKeyWord = "";


    public newsFragment(){}

    public void setSearchKeyWord(String key)
    {
        this.searchKeyWord = key;
        //改变内容
    }

    public static newsFragment newInstance()
    {
        newsFragment fragment = new newsFragment();
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_page, container,false);
        this.newsViewPaper = (ViewPager) view.findViewById(R.id.view_pager);
        this.newsViewPaper.setOffscreenPageLimit(3);

        this.newsTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        //add Tablayout

        this.newsTabLayout.addTab(this.newsTabLayout.newTab().setText("tab1"));
        this.newsTabLayout.addTab(this.newsTabLayout.newTab().setText("tab2"));
        this.newsTabLayout.addTab(this.newsTabLayout.newTab().setText("tab3"));
        this.newsTabLayout.addTab(this.newsTabLayout.newTab().setText("tab4"));
        this.newsTabLayout.addTab(this.newsTabLayout.newTab().setText("tab5"));
        this.newsTabLayout.addTab(this.newsTabLayout.newTab().setText("tab6"));


        //this.newsViewPaper.setAdapter();
        this.newsTabLayout.setupWithViewPager(this.newsViewPaper);

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    private class newsPaperAdapter extends FragmentStatePagerAdapter
    {

        public newsPaperAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

}
