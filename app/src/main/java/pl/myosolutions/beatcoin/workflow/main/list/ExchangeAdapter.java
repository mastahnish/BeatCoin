package pl.myosolutions.beatcoin.workflow.main.list;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.myosolutions.beatcoin.databinding.ExchangeItemBinding;
import pl.myosolutions.beatcoin.model.ExchangeItem;

/**
 * Created by Jacek on 2017-09-27.
 */

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ExchangeItemViewHolder> {

    private List<ExchangeItem> mExchangeItems;
    private OnItemClickListener onItemClickListener;


    public ExchangeAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ExchangeAdapter(List<ExchangeItem> items, OnItemClickListener listener) {
        this.mExchangeItems = items;
        this.onItemClickListener = listener;
    }

    public void refreshData(List<ExchangeItem> items){
        if(items == null){
            return;
        }

        this.mExchangeItems = items;
        notifyDataSetChanged();
    }

    @Override
    public ExchangeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExchangeItemBinding binding = ExchangeItemBinding.inflate(inflater, parent, false);
        return new ExchangeItemViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ExchangeItemViewHolder holder, int position) {
            ExchangeItem exchangeItem = mExchangeItems.get(position);
            holder.binding.setExchangeItem(exchangeItem);
    }

    @Override
    public int getItemCount() {
        return mExchangeItems!=null ? mExchangeItems.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ExchangeItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ExchangeItemBinding binding;

        public ExchangeItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        public ExchangeItemBinding getBinding() {
            return binding;
        }


        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(mExchangeItems.get(getAdapterPosition()));
        }
    }
    public interface OnItemClickListener {
        void onItemClick(ExchangeItem item);
    }


}
