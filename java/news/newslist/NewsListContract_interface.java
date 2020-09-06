package news.newslist;

import android.os.Bundle;

import com.example.uitest.MainContract_interface;

import java.util.List;

import Base.Presenter_interface;
import Base.View_interface;
import data.NewsItem;

public interface NewsListContract_interface {

    interface View extends View_interface<Presenter>
    {

        /*
        设置新闻列表（填充新闻）,初始化，可能调用多次
         */
        void setNewsList(List<NewsItem> list);
        /*
        补充新闻列表（上拉获取更多）
         */
        void appendNewsList(List<NewsItem> list);
        /*
         * 重置
         * @param pos
         * @param has_read
         */
        void resetItemRead(int pos, boolean has_read);
        /*
         * 获取新闻成功
         * @param loadCompleted 是否加载完成
         */
        void OnSuccess(boolean loadCompleted);
        /*
        获取新闻失败
         */
        void onError();

    }

    interface Presenter extends Presenter_interface
    {
        /*
        是否正在加载
         */
        boolean isLoading();
        /*
        上拉获取更多新闻
         */
        void requireMoreNews();
        /*
        下拉更新
         */
        void refreshNews();
        /*
        获取新闻详情
         */
        void openNewsDetail(NewsItem news, Bundle options);
        /*
        从数据库载入是否已读
         */
        void fetchNewsRead(int posision, NewsItem news);
        /*
        设置搜索关键字
         */
        void setKeyWord(String keyWord);
    }

}
