package sumati.com.nrscproject.rest.model;

public class StoreResponse {

    String medicineName;
    String medicineType;

    public StoreResponse(String medicineName, String medicineType) {
        this.medicineName = medicineName;
        this.medicineType = medicineType;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getMedicineType() {
        return medicineType;
    }
}
