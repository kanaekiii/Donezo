package com.codsoft.todo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private RadioButton radioDone;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    private List<DataClass> dataList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataClass data = dataList.get(position);
        holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recDesc.setText(dataList.get(position).getDataTime());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Time", dataList.get(holder.getAdapterPosition()).getDataTime());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("Key", dataList.get(holder.getAdapterPosition()).getKey());

                context.startActivity(intent);
            }
        });

        holder.radioDone.setChecked(data.isChecked());

        holder.radioDone.setOnClickListener(v -> {
            boolean isChecked = holder.radioDone.isChecked();

            data.setChecked(isChecked);

            if (isChecked) {
                holder.recTitle.setPaintFlags(holder.recTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.recDesc.setPaintFlags(holder.recDesc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.recTitle.setPaintFlags(holder.recTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                holder.recDesc.setPaintFlags(holder.recDesc.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    TextView recTitle, recDesc;
    CardView recCard;
    RadioButton radioDone;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recTitle = itemView.findViewById(R.id.recTitle);
        recDesc = itemView.findViewById(R.id.recDesc);
        recCard = itemView.findViewById(R.id.recCard);
        radioDone = itemView.findViewById(R.id.radioDone);

    }
}