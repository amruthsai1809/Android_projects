package com.example.amruth.tedradio;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by amruth on 11/03/17.
 */

public class LinearAdapter  extends RecyclerView.Adapter<LinearAdapter.Holder>{
    List<Item> listData;
    LayoutInflater inflater;
    ItemClickCallback itemClickCallback;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.list_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        SimpleDateFormat sd= new SimpleDateFormat("EEE, dd, MMM yyyy");
        Item item= listData.get(position);
        holder.title.setText(item.getTitle());
        holder.date.setText(sd.format(item.getDate()));
        holder.play.setImageResource(R.drawable.play);
        holder.playnow.setText("play now");
       // holder.imageView.setAlpha(100);
        Picasso.with((Context) itemClickCallback).load(item.getImageurl()).into(holder.imageView);
        //holder.play.setBackgroundResource(R.drawable.playwhite);
        //holder.imageView.setBackgroundResource(R.drawable.play);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public interface ItemClickCallback{
        void onItemClick(int p);
        void onSeconddaryclick(int p);
    }
    public LinearAdapter(List<Item> listData, Context c){
        this.inflater=LayoutInflater.from(c);
        itemClickCallback= (ItemClickCallback) c;
        this.listData=listData;
    }
    class Holder extends RecyclerView.ViewHolder{
        View container;
        ImageView imageView;
        TextView title,date,playnow;
        ImageButton play;

        public Holder(View itemView) {
            super(itemView);
            container=itemView.findViewById(R.id.container);
            imageView= (ImageView) itemView.findViewById(R.id.mainimage);
            title= (TextView) itemView.findViewById(R.id.title);
            date= (TextView) itemView.findViewById(R.id.date);
            playnow= (TextView) itemView.findViewById(R.id.playnow);
            play= (ImageButton) itemView.findViewById(R.id.play);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickCallback.onItemClick(getAdapterPosition());
                }
            });
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickCallback.onSeconddaryclick(getAdapterPosition());
                }
            });
        }
    }
}
