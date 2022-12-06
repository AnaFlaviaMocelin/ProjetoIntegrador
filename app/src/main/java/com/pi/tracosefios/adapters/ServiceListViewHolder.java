package com.pi.tracosefios.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pi.tracosefios.R;

public class ServiceListViewHolder extends RecyclerView.ViewHolder {

    TextView title, description;
    Button edit_button, delete_button;

    public ServiceListViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.service_title);
        description = itemView.findViewById(R.id.service_description);
        edit_button = itemView.findViewById(R.id.edit_button);
        delete_button = itemView.findViewById(R.id.delete_button);
    }
}
