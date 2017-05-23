package com.zilin.zaccount.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zilin.zaccount.R;
import com.zilin.zaccount.bean.AccountBean;
import com.zilin.zaccount.common.Global;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zning on 2016/4/19.
 */
public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.MyViewHolder> {

    private List<AccountBean> dataList = new ArrayList<>();
    private LayoutInflater inflater;
    private IListener il;

    public AccountAdapter(IListener il) {
        this.il = il;
    }

    public void setData(List<AccountBean> addList) {
        this.dataList = addList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        View view = inflater.inflate(R.layout.item_all_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final AccountBean bean = dataList.get(position);
        if (bean == null) {
            return;
        }
        holder.setTitle(bean.getInfo());
        holder.setTime(bean.getTime());
        holder.setMoneyTv("¥"+bean.getMoney());
        holder.setStatus(TextUtils.equals(bean.getName(), Global.NAME_IN));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (il != null) {
                    il.onClick(bean);
                }
            }
        });

        holder.delIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (il != null) {
                    il.onDel(bean);
                }
                dataList.remove(bean);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTv, moneyTv, timeTv;
        private ImageView delIv, statusIv;

        public MyViewHolder(View view) {
            super(view);
            titleTv = (TextView) view.findViewById(R.id.item_tv_title);
            moneyTv = (TextView) view.findViewById(R.id.item_tv_money);
            timeTv = (TextView) view.findViewById(R.id.item_tv_time);
            delIv = (ImageView) view.findViewById(R.id.item_iv_del);
            statusIv = (ImageView) view.findViewById(R.id.item_iv_status);
        }

        public void setTitle(String title) {
            titleTv.setText(title);
        }
        public void setTime(String time) {
                timeTv.setText(time);
        }
        public void setMoneyTv(String money) {
            moneyTv.setText(money);
        }
        public void setStatus(boolean flag) {
            statusIv.setSelected(flag);
        }
    }

    public interface IListener {
        void onClick(AccountBean item);
        void onDel(AccountBean item);
    }
}
