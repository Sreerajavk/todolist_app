package com.example.sreeraj.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 import android.content.Context;
 import android.support.v7.widget.RecyclerView;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ImageView;
 import android.widget.TextView;


 import java.util.List;

 /**
 * Created by Belal on 10/18/2017.
 */


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Content> contentList;



    //getting the context and product list with constructor
    public ProductAdapter(Context mCtx, List<Content> contentList) {
        this.mCtx = mCtx;
        this.contentList = contentList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview , parent , false);

        // view.getLayoutParams().width = parentViewGroup.getWidth();
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        //getting the product of the specified position
        final Content content = contentList.get(position);

        //binding the data with the viewholder views
        holder.heading.setText(content.getHeading());
        holder.context.setText(content.getContext());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx , Main3Activity.class);
                intent.putExtra("heading" , content.getHeading());
                intent.putExtra("context" , content.getContext());
                intent.putExtra("position",position );
                mCtx.startActivity(intent);
                ((Activity)mCtx).finish();

            }
        });

    }


    @Override
    public int getItemCount() {
        return contentList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView heading , context;
        ImageView imageView;
        LinearLayout linearLayout;

        public ProductViewHolder(View itemView) {
            super(itemView);

            heading = itemView.findViewById(R.id.textview1);
            context = itemView.findViewById(R.id.textview2);
            linearLayout = itemView.findViewById(R.id.linearLayout);

        }
    }
}
