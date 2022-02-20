package com.example.eemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.cViewholder> {

    Context context;
    ArrayList<contacts> data = new ArrayList<>();

    public AdapterClass(@NonNull Context context , ArrayList<contacts> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public cViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.contacts, parent, false
        );
        return new cViewholder(view);
    }

    @Override
    public void onBindViewHolder(cViewholder holder, int position) {

        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class cViewholder extends RecyclerView.ViewHolder{
        public cViewholder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(contacts item){

            TextView name = itemView.findViewById(R.id.contactName);
            name.setText(item.name);
            TextView number = itemView.findViewById(R.id.contactNumber);
            number.setText(item.number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                }
            });

        }
    }
}
