package sumati.com.nrscproject.rest.Request;

import android.graphics.Point;

import lombok.Data;

@Data
public class MedicineSearchRequest {
    String medicineName;

    int distanceInMeters;

    Point currentLocation;

}