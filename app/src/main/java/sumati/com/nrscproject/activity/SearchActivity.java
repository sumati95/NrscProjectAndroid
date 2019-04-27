package sumati.com.nrscproject.activity;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sumati.com.nrscproject.rest.model.Interface;
import sumati.com.nrscproject.R;
import sumati.com.nrscproject.rest.model.MedicineSearchResponse;
import sumati.com.nrscproject.rest.model.Store;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SearchActivity extends AppCompatActivity {

    String medicineName;
    int distanceInMeters;

    private Toolbar toolbar;
    private EditText editSearch;
    private EditText editDistance;
    private EditText editLocation;
    private TextView txtDistance;
    private Button btnSearch;
    private Button btnLocation;

    private FusedLocationProviderClient client;


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/").addConverterFactory(GsonConverterFactory.create())
            .build();

    Interface interfaceService = retrofit.create(Interface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editSearch = (EditText) findViewById(R.id.editSearch);
        editDistance = (EditText) findViewById(R.id.editDistance);
        editLocation = (EditText) findViewById(R.id.editLocation);
        txtDistance = (TextView) findViewById(R.id.txtDistance);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnLocation = (Button) findViewById(R.id.btnLocation);

        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(SearchActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                client.getLastLocation().addOnSuccessListener(SearchActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            editLocation.setText(location.toString());
                        }
                    }
                });
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineName = editSearch.getText().toString();
                distanceInMeters = Integer.parseInt(editDistance.getText().toString());

                Call <MedicineSearchResponse> call = interfaceService.getMedicinesDetails(medicineName, distanceInMeters);
                call.enqueue(new Callback<MedicineSearchResponse>() {

                    @Override
                    public void onResponse(Call<MedicineSearchResponse> call, Response<MedicineSearchResponse> response) {
                        List<Store> stores = response.body().getResults();
                    }

                    @Override
                    public void onFailure(Call<MedicineSearchResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        };

        MenuItem searchItem = menu.findItem(R.id.action_search);

        searchItem.setOnActionExpandListener(onActionExpandListener);

        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new  String[] {ACCESS_FINE_LOCATION}, 1);
    }
}
