package news.newslist;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import data.Constant;
import data.NewsItem;

public class NewsListPresenter implements NewsListContract_interface.Presenter {

    private final int PAGE_SIZE = 20;

    private NewsListContract_interface.View view;
    private int category_id;
    private String mykeyword;
    private int PageNum = 1;
    private boolean isLoading = false;
    //private long mLastFetchStart;

    public NewsListPresenter(NewsListContract_interface.View view, int category_id, String keyword)
    {
        this.view = view;
        this.category_id = category_id;
        this.mykeyword = keyword;
        view.setPresenter(this);
    }

    @Override
    public boolean isLoading() { return this.isLoading; }

    @Override
    public void requireMoreNews() {
        Log.i("NewlistPresenter","requiremorenews" + PageNum);
        this.PageNum += 1;
        obtainNews();
    }

    @Override
    public void refreshNews() {
        Log.i("NewlistPresenter","refreshnews" + PageNum);
        this.PageNum = 1;
        obtainNews();
    }

    @Override
    //打开某一新闻详细页面
    public void openNewsDetail(NewsItem news, Bundle options) {
        //TODO Detialnews
    }

    @Override
    public void fetchNewsRead(int posision, NewsItem news) {

    }

    @Override
    public void setKeyWord(String keyWord) {
        this.mykeyword = keyWord;
        this.refreshNews();
    }

    @Override
    public void subscribe() {
        refreshNews();
    }

    @Override
    public void unsubsribe() {
        //donothing
    }
    //从数据库中获取新闻
    private void obtainNews(){
        //TODO getNewsFromDatabase
        List<NewsItem> list = new ArrayList<NewsItem>();
        for (int i = 0;i < PAGE_SIZE * PageNum;i++)
        {
            NewsItem item = new NewsItem();
            item.news_Title = i + "test" + "tsinghua university" + " " + Constant.CATEGERIES[category_id];
            item.news_Time = "2020/9/5";
            list.add(item);
        }
        this.view.setNewsList(list);
        this.view.OnSuccess(false);
    }
}
