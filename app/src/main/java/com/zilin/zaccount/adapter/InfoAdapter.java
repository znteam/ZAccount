package com.zilin.zaccount.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.zilin.zaccount.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zning on 2016/4/19.
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewHolder> {

    private List<String> dataList = new ArrayList<>();
    private LayoutInflater inflater;
    private IListener il;

    public InfoAdapter(IListener il) {
        this.il = il;
    }

    public void setData(List<String> addList) {
        this.dataList = addList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        View view = inflater.inflate(R.layout.item_info_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final String bean = dataList.get(position);

        holder.setTitle(bean);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (il != null) {
                    il.onClick(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTv;

        public MyViewHolder(View view) {
            super(view);
            titleTv = (TextView) view.findViewById(R.id.item_tv_title);
        }

        public void setTitle(String title) {
            titleTv.setText(title);
        }
    }

    public interface IListener {
        void onClick(String item);
    }
}
