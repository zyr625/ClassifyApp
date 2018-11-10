package ming.abner.com.universallibrary.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ming.abner.com.universallibrary.mvp.view.AppDelegate;

public abstract class BaseFragment<T extends AppDelegate> extends Fragment{
    protected   T delegate;
    public abstract Class<T> getClassDelegate();

    public BaseFragment(){
        try {
            delegate=getClassDelegate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        delegate.create(inflater,container,savedInstanceState);
        return delegate.getRootView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        delegate.initContext(getActivity());
        initWeight();
        delegate.initData();
    }


    public void initWeight(){}
}
