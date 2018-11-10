package ming.abner.com.universallibrary.net;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ming.abner.com.universallibrary.BaseApplication;
import ming.abner.com.universallibrary.listener.BaseService;
import ming.abner.com.universallibrary.listener.HttpListener;
import ming.abner.com.universallibrary.util.CacheUtils;
import ming.abner.com.universallibrary.util.NetworkUtils;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 联网工具类
 */
public class HttpHelper {
    private  Context context;
    private  BaseService mBaseService;
    private boolean isreadCache;//默认情况下不读取缓存
    private boolean updateCache;//默认情况下更新缓存
    private String url;

    public HttpHelper(Context context){
        this.context=context;
        Retrofit retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(HttpUrl.BASE_URL_1).build();
        mBaseService=retrofit.create(BaseService.class);
    }

    //设置是否读取缓存
    public HttpHelper readCache(boolean isreadCache){
        this.isreadCache=isreadCache;
        return this;
    }

    //设置是否更新缓存
    public HttpHelper updateCache(boolean updateCache){
        this.updateCache=updateCache;
        return this;
    }

    //get请求
    public HttpHelper get(String url, Map<String,String> map){
        doHttp(0,url,map);
        return this;
    }

    private void doHttp(int i, String url, Map<String, String> map) {
        if(map==null){
            map=new HashMap<>();
        }
        this.url=url;
        if(isreadCache&&!NetworkUtils.isConnected(context)){
            String data=CacheUtils.getCacheUtils().query(url);
            listener.success(data);
            return;
        }
        Observable<ResponseBody> ob = mBaseService.get(url, map);
        if(i==1){
            ob = mBaseService.post(url, map);
        }
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //post请求
    public HttpHelper post(String url, Map<String,String> map){
        doHttp(1,url,map);
        return this;
    }

    private Observer observer=new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String data=responseBody.string();
                listener.success(data);
                if(isreadCache&&CacheUtils.getCacheUtils().query(url)==null){
                    CacheUtils.getCacheUtils().insert(data,url);
                }else{
                    if(updateCache){
                        CacheUtils.getCacheUtils().update(data,url);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {
            String error=e.getMessage();
            listener.fail(error);
        }

        @Override
        public void onComplete() {

        }
    };

    private HttpListener listener;
    public HttpHelper result(HttpListener listener){
        this.listener=listener;
        return this;
    }
}
