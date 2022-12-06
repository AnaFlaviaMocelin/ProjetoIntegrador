package com.pi.tracosefios.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pi.tracosefios.EditActivity;
import com.pi.tracosefios.R;
import com.pi.tracosefios.models.Service;

import java.util.ArrayList;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListViewHolder> {

    private Context context;
    private ArrayList<Service> services;

    public ServiceListAdapter(Context context, ArrayList<Service> services) {
        this.context = context;
        this.services = services;
    }

    @NonNull
    @Override
    public ServiceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        ServiceListViewHolder viewHolder = new ServiceListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceListViewHolder holder, int position) {
        Service service = services.get(position);
        holder.title.setText(service.getTitle());
        holder.description.setText(service.getDescription());

        holder.edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editService = new Intent(context, EditActivity.class);
                editService.putExtra("id", service.getId());
                editService.putExtra("title", service.getTitle());
                editService.putExtra("description", service.getDescription());
                context.startActivity(editService);
            }
        });


    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}
