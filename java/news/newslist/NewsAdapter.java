package news.newslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uitest.R;

import java.util.ArrayList;
import java.util.List;

import data.Constant;
import data.NewsItem;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_NORM = 0;
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_END = 2;

    private Context context;
    private List<NewsItem> newsdata = new ArrayList<NewsItem>();
    private boolean isshowend = true;
    private OnItemClickListener onItemClickListener;

    public NewsAdapter(Context context){ this.context = context; }

    public NewsItem getnewItemById(int position)
    {
        return newsdata.get(position);
    }

    public void setNewsData(List<NewsItem> data)
    {
        this.newsdata = new ArrayList<NewsItem>(data);
        //TODO 提示信息改变
        this.notifyDataSetChanged();
    }

    public void appendNewsData(List<NewsItem> data)
    {
        int joint = this.newsdata.size();
        this.newsdata.addAll(data);
        //TODO 提示信息改变
        this.notifyItemRangeChanged(joint,getItemCount());
    }

    public void removeItem(int pos)
    {
        this.newsdata.remove(pos);
        //TODO 提示信息改变
        this.notifyItemRemoved(pos);
    }

    public void setHasRead(int pos, boolean has_read)
    {
        NewsItem news = this.getnewItemById(pos);
        news.has_read = has_read;
        this.newsdata.set(pos,news);
    }

    public boolean isShowEnd() {return this.isshowend;}

    public void setEndVisible(boolean visible)
    {
        if (this.isshowend != visible){
            this.isshowend = visible;
            if (this.isshowend) this.notifyItemInserted(this.newsdata.size());
            else this.notifyItemRemoved(this.newsdata.size());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override//创建对象
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.newlist_end,parent,false);
            return new EndViewHolder(view);
        }

    }

    @Override//填充新闻内容
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder)
        {
            NewsItem newsItem = this.newsdata.get(position);
            final ItemViewHolder item = (ItemViewHolder) holder;
            item.Title.setText(newsItem.news_Title);
            item.Date.setText(newsItem.news_Time);
            item.myimageview.setImageBitmap(null);
            item.PositioninList = position;
        }
    }

    @Override
    public int getItemCount() {
        if (this.isshowend) return this.newsdata.size() + 1;
        else return this.newsdata.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == this.newsdata.size() && isshowend == true) return TYPE_END;
        else return TYPE_NORM;
    }

    /*
        新闻单元格的listener
         */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /*
    列表内容
     */

    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View myview;
        TextView Title,Date;
        int PositioninList = -1;
        ImageView myimageview;

        public ItemViewHolder(View view) {
            super(view);
            this.myview = view;
            Title = (TextView) view.findViewById(R.id.text_title);
            Date = (TextView) view.findViewById(R.id.text_date);
            myimageview = (ImageView) view.findViewById(R.id.image_view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view,this.getLayoutPosition());
            }
        }
    }

    /*
    列表底部
     */
    public class EndViewHolder extends RecyclerView.ViewHolder
    {
        public EndViewHolder(View view) {super(view);}
    }

}
