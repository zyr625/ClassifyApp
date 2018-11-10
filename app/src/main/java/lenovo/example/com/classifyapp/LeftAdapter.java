package lenovo.example.com.classifyapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
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
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {
    private Context context;
    private List<LeftBean.DataBean> list = new ArrayList<>();

    public LeftAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LeftAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.adapter_left, null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeftAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.tvName.setText(list.get(i).getName());
        if (list.get(i).isSelected()){
            myViewHolder.tvName.setTextColor(Color.parseColor("#222222"));
            myViewHolder.itemView.setBackgroundColor(Color.WHITE);
            myViewHolder.viewLine.setVisibility(View.VISIBLE);
        }else {
            myViewHolder.tvName.setTextColor(Color.parseColor("#999999"));
            myViewHolder.itemView.setBackgroundColor(Color.parseColor("#e8e8e8"));
            myViewHolder.viewLine.setVisibility(View.GONE);
        }
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClick(list.get(i).getCid());
                for (int j = 0; j < list.size(); j++) {
                    if (i==j){
                        list.get(j).setSelected(true);
                    }else{
                        list.get(j).setSelected(false);
                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<LeftBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        View viewLine;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            viewLine=itemView.findViewById(R.id.view_line);
        }
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public interface OnItemClickListener{
        void itemClick(int cid);
    }
}
