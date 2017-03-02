package com.zhengxin.elevatorassistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zhengxin.elevatorassistant.R;
import com.zhengxin.elevatorassistant.model.CeShi;

import java.util.List;

/**
 * Created by Administrator on 2017/2/28.
 */

public class CeshiAdapter extends RecyclerView.Adapter<CeshiAdapter.MyViewHolder>{
    private Context context;
    private List<CeShi> ceShis;

    public List<CeShi> getCeShis() {
        return ceShis;
    }

    public void setCeShis(List<CeShi> ceShis) {
        this.ceShis = ceShis;
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
    private OnItemClickLitener mOnItemClickLitener;
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    public CeshiAdapter (Context context,List<CeShi> ceShis){
        this.ceShis=ceShis;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_ceshi, parent,
                false));
        return holder;
    }

    @Override
    public int getItemCount() {
        return ceShis.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final  int position) {//赋值以及逻辑处理。
        holder.tv_item.setText(ceShis.get(position).getName());
        if (mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item= (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
    public void addData(int position) {
        ceShis.add(position, new CeShi());
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        ceShis.remove(position);
        notifyItemRemoved(position);
    }
}
