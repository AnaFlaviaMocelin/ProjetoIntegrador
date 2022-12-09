package com.pi.tracosefios.services;

import com.pi.tracosefios.models.Service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class EditService {

    private RequestService requestService = new RequestService();
    private Response response;

    public String editValues(Service service) throws JSONException, IOException {
        JSONObject serviceJson = new JSONObject();
        serviceJson.put("id", service.getId());
        serviceJson.put("name", service.getTitle());
        serviceJson.put("description", service.getDescription());

        System.out.println(String.valueOf(serviceJson));
        response = requestService.send("http://192.168.100.200:3333/services/updateService", String.valueOf(serviceJson));

        if (response.isSuccessful()){
            return "Valores atualizados com sucesso.";
        }
        return "Erro ao atualizar os valores \nERRO: " +response.body();

    }


}
