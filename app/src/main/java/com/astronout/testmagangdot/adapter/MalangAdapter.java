package com.astronout.testmagangdot.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.astronout.testmagangdot.DetailActivity;
import com.astronout.testmagangdot.R;
import com.astronout.testmagangdot.model.Malang;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MalangAdapter extends RecyclerView.Adapter<MalangAdapter.ViewHolder> {

    private Context context;
    private List<Malang> malangList;

    public static final String EXTRA_IMAGE = "EXTRA_IMAGE";
    public static final String EXTRA_CAPTION = "EXTRA_CAPTION";

    public MalangAdapter(Context context, List<Malang> malangList) {
        this.context = context;
        this.malangList = malangList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_malang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(malangList.get(position).getmPoster())
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        Log.d("ItemCount", String.valueOf(malangList.size()));
        return malangList.size();
//        return 42;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_poster)
        ImageView imgPoster;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(EXTRA_IMAGE, malangList.get(pos).getmImage());
                    intent.putExtra(EXTRA_CAPTION, malangList.get(pos).getmCaption());
                    context.startActivity(intent);
                }
            });
        }
    }

}
