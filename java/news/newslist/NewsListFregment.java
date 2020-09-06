package news.newslist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.uitest.R;

import java.util.List;

import data.NewsItem;

public class NewsListFregment extends Fragment implements NewsListContract_interface.View {

    private NewsListContract_interface.Presenter newlistpresenter;
    private int category_id;
    private String keyword;

    private int LastClickPosition = -1;
    private SwipeRefreshLayout swipeRefreshLayout;//下拉刷新控件
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private View noInfoView;

    public NewsListFregment(){}

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
        Bundle bundle = this.getArguments();
        bundle.putString("keyword",keyword);
        this.setArguments(bundle);
    }

    public String getKeyword() {return this.keyword;}

    public static NewsListFregment newInstance(int category_id,String keyword)
    {
        Bundle args = new Bundle();
        NewsListFregment f = new NewsListFregment();
        args.putInt("category",category_id);
        args.putString("keyword",keyword);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.LastClickPosition = -1;
        this.category_id = this.getArguments().getInt("category");
        this.keyword = this.getArguments().getString("keyword");
        this.newlistpresenter = new NewsListPresenter(this,category_id,keyword);

        this.newsAdapter = new NewsAdapter(this.getContext());
        this.newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsItem item = newsAdapter.getnewItemById(position);
                Log.i("Newsfragment","You have clicked id:" + String.valueOf(position) + " " + item.news_Title);
                //TODO newdetialfragment
            }
        });

        //this.newlistpresenter.subscribe();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list,container,false);
        this.noInfoView = view.findViewById(R.id.text_empty);
        this.noInfoView.setVisibility(this.newsAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);

        this.swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_widget);
        this.swipeRefreshLayout.setOnRefreshListener(() -> {
            this.swipeRefreshLayout.setRefreshing(true);
            this.newlistpresenter.refreshNews();
        });

        this.linearLayoutManager = new LinearLayoutManager(this.getContext());
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        this.recyclerView.setLayoutManager(this.linearLayoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.newsAdapter);
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("NewListFragment","1" + String.valueOf(newState == RecyclerView.SCROLL_STATE_IDLE));
                Log.i("NewListFragment","2" + String.valueOf(lastVisibleItem == newsAdapter.getItemCount() - 1));
                Log.i("NewListFragment","3" + String.valueOf(!newlistpresenter.isLoading()));
                Log.i("NewListFragment","4" + String.valueOf(newsAdapter.isShowEnd()));
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem == newsAdapter.getItemCount() - 1
                && !newlistpresenter.isLoading() && newsAdapter.isShowEnd()){
                    Log.i("NewListFragment","5" + "inforequiremmorenews");
                    newlistpresenter.requireMoreNews();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            }
        });
        this.newlistpresenter.subscribe();
        return view;
    }

    @Override
    public void setNewsList(List<NewsItem> list) {
        newsAdapter.setNewsData(list);
        //Log.i("NewListFragment",String.valueOf(this.noInfoView == null));
        this.noInfoView.setVisibility(newsAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void appendNewsList(List<NewsItem> list) {
        newsAdapter.appendNewsData(list);
    }

    @Override
    public void resetItemRead(int pos, boolean has_read) {
        if (this.newsAdapter.getnewItemById(pos).has_read != has_read){
            this.newsAdapter.setHasRead(pos, has_read);
            this.newsAdapter.notifyItemChanged(pos);
        }
    }

    @Override
    public void OnSuccess(boolean loadCompleted) {
        this.newsAdapter.setEndVisible(!loadCompleted);
        this.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError() {
        this.swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(),"获取新闻失败，请重试",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(NewsListContract_interface.Presenter presenter) {
        this.newlistpresenter = presenter;
    }

    @Override
    public void startAc(Intent intent, Bundle bundle) {
        this.startActivity(intent,bundle);
    }
}
