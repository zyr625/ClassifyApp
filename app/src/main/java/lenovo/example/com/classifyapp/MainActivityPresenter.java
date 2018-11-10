package lenovo.example.com.classifyapp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ming.abner.com.universallibrary.listener.HttpListener;
import ming.abner.com.universallibrary.mvp.view.AppDelegate;
import ming.abner.com.universallibrary.net.HttpHelper;

/**
 * author：shashe
 * 日期：2018/11/10
 */
public class MainActivityPresenter extends AppDelegate {
    private RecyclerView left_recyclerView, right_recyclerView;
    private Context context;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        left_recyclerView = get(R.id.left_recycler);
        right_recyclerView = get(R.id.right_recycler);
        leftAdapter = new LeftAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        left_recyclerView.setLayoutManager(linearLayoutManager);
        left_recyclerView.setAdapter(leftAdapter);

        rightAdapter = new RightAdapter(context);
        LinearLayoutManager linearLayoutManagerRight = new LinearLayoutManager(context);
        linearLayoutManagerRight.setOrientation(LinearLayoutManager.VERTICAL);
        right_recyclerView.setLayoutManager(linearLayoutManagerRight);
        right_recyclerView.setAdapter(rightAdapter);
        leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int cid) {
                doRightData(cid);
            }
        });
        doLeftData();
        doRightData(1);
    }

    //请求右边数据
    private void doRightData(int cid) {
        Map<String,String> map=new HashMap<>();
        map.put("cid",cid+"");
        new HttpHelper(context).result(new HttpListener() {
            @Override
            public void success(String data) {
                RightBean rightBean = new Gson().fromJson(data, RightBean.class);
                rightAdapter.setList(rightBean.getData());
            }

            @Override
            public void fail(String error) {

            }
        }).get("/product/getProductCatagory",map);
    }

    //左边数据展示
    private void doLeftData() {
        new HttpHelper(context).result(new HttpListener() {
            @Override
            public void success(String data) {
                LeftBean leftBean = new Gson().fromJson(data, LeftBean.class);
                leftBean.getData().get(0).setSelected(true);
                leftAdapter.setList(leftBean.getData());
            }

            @Override
            public void fail(String error) {

            }
        }).get("/product/getCatagory",null);
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    //重新获取焦点
    public void onResume() {
    }

    //销毁方法
    public void onDestroy() {
    }
}
