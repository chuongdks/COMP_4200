package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    // Member has Arraylist of Text n Image and Context
    ArrayList<MyDataSet> dataList;
    Context context;

    // Constructor
    public MyAdapter(ArrayList<MyDataSet> data, Context context) {
        this.dataList = data;
        this.context = context;
    }

    // WHat is MyViewHolder (https://stackoverflow.com/questions/59919366/what-is-recyclerview-adaptermyadapter-myviewholder-and-how-it-is-different-fro) and (https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder)
    // Basically set up by using the file card_view.xml, card_view's "view" is used below
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false); // take
        return new MyViewHolder(view);
    }

    // What is onBindViewHolder method (https://stackoverflow.com/questions/37523308/when-onbindviewholder-is-called-and-how-it-works)
    // Basically set the data up from MyDataSet and optional function for the cardView
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyDataSet data = dataList.get(position);
        holder.imageView.setImageResource(data.getImage());
        holder.textView.setText(data.getText());
        holder.colorButton.setText(data.getColorText());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 1) {
                    Intent intent = new Intent(context.getApplicationContext(), Card2Activity.class);
                    context.startActivity(intent);
                }
                else {
                    String msg = "You click a card" + (position + 1);
                    Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Change Text Color based on the Button Text
        holder.colorButton.setOnClickListener(new View.OnClickListener() {
            boolean isWhite = false;                    // Toggle Text Color flag
            @Override
            public void onClick(View v) {
                if (!isWhite) {
                    int color = Color.parseColor(data.getColorText().toLowerCase());
                    holder.textView.setTextColor(color);
                    holder.colorButton.setText("White");
                    isWhite = true;
                }
                else {
                    holder.textView.setTextColor(Color.WHITE);
                    holder.colorButton.setText(data.getColorText());
                    isWhite = false;
                }
            }
        });

        // Change the text when click on text view
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.textView.setText("Text " + (position + 1));
            }
        });
    }

    // getItemCount() doc: Returns the total number of items in the data set held by the adapter.
    @Override
    public int getItemCount() {
        return 2; // Math.min(dataList.size(), 2);
    }

    // ViewHolder class
    // Basically "main" class that has image, text and card info of each cardView
    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView cardView;
        Button colorButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.tv_card);
            cardView = itemView.findViewById(R.id.card_view_test);
            colorButton = itemView.findViewById(R.id.bt_color);
        }
    }
}
