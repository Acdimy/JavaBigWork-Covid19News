package data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

    public static class Category
    {
        public String title;
        public int idx;
        Category(String t, int i)
        {
            this.title = t;
            this.idx = i;
        }
    }


    //还需要一个保存线程，来保存设置
    private String save_path;//设置的保存文件
    private List<Integer> available_categories;//可见的新闻分类

    Configuration(Context context)
    {
        save_path = context.getFilesDir().getPath() + "/configuration.json";
        loadConfig();
    }

    private void loadConfig(){};//载入用户设置

    private void saveConfig(){};//保存用户设置

    /*
    所有分类
    @return
     */
    public List<Category> allCategories()
    {
        List<Category> list = new ArrayList<>();
        return list;
    }
    /*
    已选分类
    @return
     */
    public List<Category> availableCategoreis()
    {
        List<Category> list = new ArrayList<>();
        return list;
    }
    /*
    未选分类
    @return
    */
    public List<Category> unavailableCategories()
    {
        List<Category> list = new ArrayList<>();
        return list;
    }
    /*
    添加分类
    @param category 分类
    @return 是否添加成功
     */
    public boolean addCategory(Category category)
    {
        return true;
    }
    /*
    删除分类
    @param category 分类
    @return 是否删除成功
    */
    public boolean removeCategory(Category category)
    {
        return true;
    }
}
