package com.marcus.architecturetests.ui.adapter;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.marcus.architecturetests.R;
import com.marcus.architecturetests.databinding.PeopleItemBinding;
import com.marcus.architecturetests.vo.People;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.BaseViewHolder<PeopleItemBinding>> {

    @Nullable
    private List<People> items;

    public PeopleAdapter(List<People> items) {
        this.items = items;
    }

    @Override
    public BaseViewHolder<PeopleItemBinding> onCreateViewHolder(ViewGroup viewGroup, int i) {
        PeopleItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.people_item, viewGroup, false);
        return new BaseViewHolder<>(binding);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<PeopleItemBinding> peopleItemBindingBaseViewHolder, int i) {
        People people = items.get(i);
        peopleItemBindingBaseViewHolder.binding.peopleName.setText(people.getName());
    }

    class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
        final T binding;

        BaseViewHolder(T binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
