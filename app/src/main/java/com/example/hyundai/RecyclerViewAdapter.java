package com.example.hyundai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.hyundai.pojo.Users;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<com.example.hyundai.RecyclerViewAdapter.ViewHolder> {
    private List<Users.Datum> data;
    private com.example.hyundai.RecyclerViewAdapter.ClickListener clickListener;
    private Context context;
    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Object getrow = data.get(position);
        LinkedTreeMap<Object,Object> t = (LinkedTreeMap) getrow;
        holder.txtName.setText(t.get("first_name").toString());
        holder.txtPhoneNumber.setText(t.get("email").toString());

        Glide.with(this.context)
                .load(t.get("avatar"))
                .transform(new CircleCrop())
                .override(200, 200)
                .into(holder.profile_pic);
       // holder.profile_pic.setI(data.get(position).percentChange1h + "%");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtPhoneNumber;
        private ImageView profile_pic;

        private ConstraintLayout constraintLayoutContainer;

        ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.textViewName);
            txtPhoneNumber = itemView.findViewById(R.id.textViewPhone);
            profile_pic = itemView.findViewById(R.id.profile_pic);

            constraintLayoutContainer = itemView.findViewById(R.id.constraintLayout);

            constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Object getrow = data.get(getAdapterPosition());
                    LinkedTreeMap<Object,Object> data_value = (LinkedTreeMap) getrow;
                    clickListener.launchIntent(data_value);
                }
            });
        }
    }

    public interface ClickListener {
        void launchIntent(LinkedTreeMap<Object,Object> name);
    }

    public void setData(List<Users.Datum> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}

