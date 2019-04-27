package sumati.com.nrscproject.rest.model;

import android.graphics.Point;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sumati.com.nrscproject.rest.Request.MedicineSearchRequest;
import sumati.com.nrscproject.rest.model.MedicineSearchResponse;

public interface Interface {

    String BASE_URL = "http://10.0.2.2:8080";

    @POST("searchForMedicines")
    Call<MedicineSearchResponse> getMedicinesDetails(@Body MedicineSearchRequest medicineSearchRequest);
}
