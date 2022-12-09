package com.pi.tracosefios.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pi.tracosefios.EditActivity;
import com.pi.tracosefios.R;
import com.pi.tracosefios.ServicesActivity;
import com.pi.tracosefios.models.Service;
import com.pi.tracosefios.services.RequestService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListViewHolder> {

    private Context context;
    private ArrayList<Service> services;
    private RequestService requestService = new RequestService();
    private Response response;

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

        holder.edit_button.setOnClickListener(view -> {
                Intent editService = new Intent(context, EditActivity.class);
                editService.putExtra("id", service.getId());
                editService.putExtra("title", service.getTitle());
                editService.putExtra("description", service.getDescription());
                context.startActivity(editService);

        });

        holder.delete_button.setOnClickListener(view -> {
            JSONObject serviceJson = new JSONObject();
            try {
                serviceJson.put("id", service.getId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                response = requestService.send("http://192.168.100.200:3333/services/deleteService", String.valueOf(serviceJson));

                if(response.isSuccessful()) {
                    context.startActivity(new Intent(context, ServicesActivity.class));
                    Toast.makeText(context, "Serviço deletado com sucesso!", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}
