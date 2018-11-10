package ming.abner.com.universallibrary.mvp.view;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *所有的presenter的一个父类
 */
public abstract class AppDelegate implements IDelegate{
    private View rootView;

    @Override
    public void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
       rootView= inflater.inflate(getLayoutId(),viewGroup,false);
    }

    //由子类传递过来的layout
    protected abstract int getLayoutId();

    @Override
    public View getRootView() {
        return rootView;
    }

    private SparseArray<View> views=new SparseArray<>();

    //传进id,返回控件
    public <T extends View> T get(int id){
        T view= (T) views.get(id);
        if(view==null){
            view=rootView.findViewById(id);
            views.put(id,view);
        }
        return view;
    }

    //设置点击事件
    public void setOnClick(View.OnClickListener listener,int... ids){
        if(ids==null){
            return;
        }
        for (int id:ids){
            get(id).setOnClickListener(listener);
        }
    }
}
