package lenovo.example.com.classifyapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * author：shashe
 * 日期：2018/11/10
 */
public class RightChildAdapter extends RecyclerView.Adapter<RightChildAdapter.MyViewHolder> {
    private List<RightBean.DataBean.ListBean> list = new ArrayList<>();
    private Context context;

    public RightChildAdapter(Context context, List<RightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list=list;
    }

    @NonNull
    @Override
    public RightChildAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.adapter_right_child, null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RightChildAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.mTextView.setText(list.get(i).getName());
        myViewHolder.mSimpleDraweeView.setImageURI(list.get(i).getIcon());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        SimpleDraweeView mSimpleDraweeView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView=itemView.findViewById(R.id.tv_name);
            mSimpleDraweeView=itemView.findViewById(R.id.simple);
        }
    }
}
