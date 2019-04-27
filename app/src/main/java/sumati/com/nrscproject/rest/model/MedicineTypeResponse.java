package sumati.com.nrscproject.rest.model;

public class MedicineTypeResponse {

    String medicineName;

    public MedicineTypeResponse(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineName() {
        return medicineName;
    }

}
