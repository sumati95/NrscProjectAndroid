package sumati.com.nrscproject.rest.model;

public class MedicineToStoreResponse {

    String medicineName;

    String storeName;

    public MedicineToStoreResponse(String medicineName, String storeName) {
        this.medicineName = medicineName;
        this.storeName = storeName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getStoreName() {
        return storeName;
    }
}
