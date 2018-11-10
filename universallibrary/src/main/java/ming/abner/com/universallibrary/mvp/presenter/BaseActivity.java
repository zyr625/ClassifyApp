package ming.abner.com.universallibrary.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ming.abner.com.universallibrary.mvp.view.AppDelegate;

public abstract class BaseActivity<T extends AppDelegate> extends AppCompatActivity{

    protected   T delegate;

    public abstract Class<T> getClassDelegate();

    public BaseActivity(){
        try {
            delegate=getClassDelegate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(delegate.getRootView());
        delegate.initContext(this);
        initWeight();
        delegate.initData();
    }

    public void initWeight(){}
}
