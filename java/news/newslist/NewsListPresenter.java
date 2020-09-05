package news.newslist;

import android.os.Bundle;

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
        this.PageNum += 1;
        obtainNews();
    }

    @Override
    public void refreshNews() {
        this.PageNum = 1;
        obtainNews();
    }

    @Override
    //打开某一新闻详细页面
    public void openNewsDetail(NewsItem news, Bundle options) {

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

    }
}
