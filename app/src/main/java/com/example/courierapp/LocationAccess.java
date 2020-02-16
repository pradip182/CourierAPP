package com.example.courierapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.courierapp.model.LatitudeLongitude;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class LocationAccess extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_access);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


        @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

            List<LatitudeLongitude> latLng = new ArrayList<>();
            latLng.add(new LatitudeLongitude(27.706195,85.3300396, "softwarica college" ));
            latLng.add(new  LatitudeLongitude(27.70482, 85.3293997, "Gopal dai ko chatamari"));
            latLng.add(new  LatitudeLongitude(27.70454, 85.3293965, "ben tea"));
            latLng.add(new  LatitudeLongitude(27.70504, 85.3293882, "haans ko choila"));

            CameraUpdate center, zoom;
            for (int i = 0; i< latLng.size(); i++){
                center=
                        CameraUpdateFactory.newLatLng(new LatLng(latLng.get(i).getLat(),
                                latLng.get(i).getLon()));

                zoom = CameraUpdateFactory.zoomTo(16);
                mMap.addMarker(new MarkerOptions().position(new LatLng(latLng.get(i).getLat(),latLng.get(i).getLon())).title(latLng.get(i).getMarker()));

            }
        }
}



//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
