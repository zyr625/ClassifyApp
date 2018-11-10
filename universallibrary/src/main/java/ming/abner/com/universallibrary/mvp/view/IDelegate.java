package ming.abner.com.universallibrary.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface IDelegate {

    //初始化
    void initData();

    //创建布局
    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    //获取View视图
    View getRootView();

    //初始化上下文
    void initContext(Context context);

}
