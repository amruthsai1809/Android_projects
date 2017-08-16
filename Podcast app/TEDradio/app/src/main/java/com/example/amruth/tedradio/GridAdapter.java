package com.example.amruth.tedradio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by amruth on 11/03/17.
 */

public class GridAdapter  extends RecyclerView.Adapter<GridAdapter.Holder>{
    List<Item> listData;
    LayoutInflater inflater;
    ItemClickCallback itemClickCallback;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.gridlist_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Item item= listData.get(position);
        holder.imageButtongrid.setAlpha(100);
        Picasso.with((Context) itemClickCallback).load(item.getImageurl()).into(holder.imageButtongrid);
        holder.titlegrid.setText(item.getTitle());
        holder.imageButtongrid.setBackgroundResource(R.drawable.play);


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public interface ItemClickCallback{
        void onItemClickgrid(int p);
        void onSeconddaryclickgrid(int p);
    }
    public GridAdapter(List<Item> listData, Context c){
        this.inflater=LayoutInflater.from(c);
        itemClickCallback= (ItemClickCallback) c;
        this.listData=listData;
    }
    class Holder extends RecyclerView.ViewHolder{
        View containergrid;
        TextView titlegrid;
        ImageButton imageButtongrid;

        public Holder(View itemView) {
            super(itemView);
            containergrid=itemView.findViewById(R.id.containergrid);
            titlegrid= (TextView) itemView.findViewById(R.id.textviewgrid);
            imageButtongrid= (ImageButton) itemView.findViewById(R.id.imagebuttongrid);
            imageButtongrid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickCallback.onSeconddaryclickgrid(getAdapterPosition());

                }
            });
            containergrid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickCallback.onItemClickgrid(getAdapterPosition());
                }
            });

        }
    }
}
