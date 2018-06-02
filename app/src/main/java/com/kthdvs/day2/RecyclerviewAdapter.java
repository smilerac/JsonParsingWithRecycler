package com.kthdvs.day2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by aayeshs on 6/2/18.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>{
    Context cxt;
    List<MenuModel>menuModels;

    public RecyclerviewAdapter(Context cxt, List<MenuModel> menuModels) {
        this.cxt = cxt;
        this.menuModels = menuModels;
    }

    @NonNull
    @Override
    public RecyclerviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter.MyViewHolder holder, int position) {
        final MenuModel menuModel=menuModels.get(position);

        holder.name.setText(menuModel.getItem_Name());
        holder.desc.setText(menuModel.getItem_Desc());
        holder.price.setText(menuModel.getItem_Price());
        //loading image in glide image view
        Glide.with(cxt).load("http://vedisapp.berlin-webdesign-agentur.de/Image/"+menuModel.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.img);

        //click listener for card
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cxt, "You clicked on"+menuModel.getItem_Name(), Toast.LENGTH_SHORT).show();
                System.out.println("name:"+menuModel.getItem_Name());
                //cxt.startActivity(new Intent(cxt,cardclick.class));//to go to another activity

                Intent intent = new Intent(cxt,cardclick.class);
                intent.putExtra("name",menuModel.getItem_Name());
                intent.putExtra("desc",menuModel.getItem_Desc());
                intent.putExtra("price",menuModel.getItem_Price());
                cxt.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc, price;
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            desc=itemView.findViewById(R.id.desc);
            price=itemView.findViewById(R.id.price);
            img=itemView.findViewById(R.id.img);
        }
    }
}
