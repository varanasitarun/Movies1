package com.example.user.moviesappstage1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private String[] img;
    public RecyclerAdapter(Context context)
    {
        this.context=context;
    }
    public void fitImages(String[] array)
    {
        this.img=array;
        notifyDataSetChanged();
    }



    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.listitems,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        String picture=BuildUrl.buildImage(img[position]);
        holder.itemView.setTag(position);
        Picasso.with(context).load(picture).fit().centerInside().into(holder.iv);


    }

    @Override
    public int getItemCount() {
        if(img==null)
        {
            return 0;
        }
        return img.length;
    }

    @Override
    public void onClick(View view) {
        int position=(int) view.getTag();
        JsonInformation.sortData(position);
        Intent intent=new Intent(context,MovieDescription.class);
        intent.putExtra(MainActivity.MAIN_TITLE,JsonInformation.getTitle());
        intent.putExtra(MainActivity.RELEASE_DATE,JsonInformation.getReleaseDate());
        intent.putExtra(MainActivity.VOTE_COUNT,JsonInformation.getVoteAverage());
        intent.putExtra(MainActivity.BACKDROP_PATH,JsonInformation.getBackdroppath());
        intent.putExtra(MainActivity.SYNOPSIS,JsonInformation.getOverview());
        context.startActivity(intent);


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.image1);
        }
    }
}
