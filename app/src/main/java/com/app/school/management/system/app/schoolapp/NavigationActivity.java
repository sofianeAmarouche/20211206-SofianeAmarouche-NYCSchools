package com.app.school.management.system.app.schoolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.app.school.management.system.app.schoolapp.databinding.ActivityNavigationBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NavigationActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ActivityNavigationBinding binding;
    private GoogleMap gmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_navigation);
        setupMapView(savedInstanceState);
    }

    private void setupMapView(Bundle savedInstanceState){
        binding.mapView.onCreate(savedInstanceState);

        binding.mapView.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        String lat = getIntent().getStringExtra("latitude");
        String lon = getIntent().getStringExtra("longitude");
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);


        LatLng ny = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ny);
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }



    @Override
    protected void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }



    @Override
    protected void onStop() {
        super.onStop();
        binding.mapView.onStop();
    }
    @Override
    protected void onPause() {
        binding.mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        binding.mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        binding.mapView.onLowMemory();
    }
}