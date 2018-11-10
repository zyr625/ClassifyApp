package lenovo.example.com.classifyapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * author：shashe
 * 日期：2018/11/10
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyViewHolder> {
    private List<RightBean.DataBean> list = new ArrayList<>();
    private Context context;

    public RightAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RightAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.adapter_right, null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RightAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.tvName.setText(list.get(i).getName());
        RightChildAdapter rightChildAdapter = new RightChildAdapter(context,list.get(i).getList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        myViewHolder.mRecycler.setLayoutManager(gridLayoutManager);
        myViewHolder.mRecycler.setAdapter(rightChildAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<RightBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        RecyclerView mRecycler;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            mRecycler=itemView.findViewById(R.id.recycler);
        }
    }
}
