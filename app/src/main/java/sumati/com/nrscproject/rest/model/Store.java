package sumati.com.nrscproject.rest.model;

import android.graphics.Point;
import android.support.v4.app.ServiceCompat;

import com.google.gson.annotations.SerializedName;

public class Store {


    @SerializedName("medicine_name")
    private String medicineName;

    @SerializedName("medicine_type")
    private String medicineType;

    @SerializedName("store_location")
    private Point storeLocation;

    @SerializedName("store_name")
    private String storeName;
}
