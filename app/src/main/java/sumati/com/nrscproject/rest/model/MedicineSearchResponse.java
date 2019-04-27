package sumati.com.nrscproject.rest.model;

import android.graphics.Point;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class MedicineSearchResponse {


    @SerializedName("medicine")
    private String medicineName;

    @SerializedName("distance")
    private int distanceInMeters;

    @SerializedName("location")
    private Point storeLocation;

    private List<Store> results;
}
