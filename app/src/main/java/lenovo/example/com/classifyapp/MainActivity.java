package lenovo.example.com.classifyapp;

import ming.abner.com.universallibrary.mvp.presenter.BaseActivity;

public class MainActivity extends BaseActivity<MainActivityPresenter> {


    @Override
    public Class<MainActivityPresenter> getClassDelegate() {
        return MainActivityPresenter.class;
    }

    @Override
    protected void onResume() {
        super.onResume();
        delegate.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        delegate.onDestroy();
    }
}
