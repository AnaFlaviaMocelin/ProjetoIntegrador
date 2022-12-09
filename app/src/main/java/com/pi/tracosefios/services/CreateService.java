package com.pi.tracosefios.services;

import com.pi.tracosefios.models.Service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class CreateService {

    private RequestService requestService = new RequestService();
    private Response response;

    public String create(Service service) throws JSONException, IOException {

        JSONObject serviceJson = new JSONObject();
        serviceJson.put("name", service.getTitle());
        serviceJson.put("description", service.getDescription());

        response = requestService.send("http://192.168.100.200:3333/services/createService", String.valueOf(serviceJson));

        if (response.isSuccessful()){
            return "Serviço criado com sucesso.";
        }
        return "Erro ao atualizar os valores \nERRO: " +response.body();
    }
}
