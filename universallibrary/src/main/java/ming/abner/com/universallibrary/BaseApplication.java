package ming.abner.com.universallibrary;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import ming.abner.com.universallibrary.util.CacheUtils;

public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        CacheUtils.getCacheUtils().init(this);
        Fresco.initialize(this);

    }

}
