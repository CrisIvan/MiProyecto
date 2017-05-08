package com.example.cristian.miproyecto;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient googleApiClient;
    private GoogleMap mMap;
    private LatLng HospitalGeneral;
    private LatLng HospitalZonSur;
    private LatLng HospitalPortada;
    private LatLng HospitalLaPaz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        HospitalGeneral = new LatLng(-16.507424, -68.118806);
        HospitalZonSur = new LatLng(-16.525705, -68.108551);
        HospitalPortada = new LatLng(-16.489011, -68.165725);
        HospitalLaPaz = new LatLng(-16.495932, -68.145837);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(LocationServices.API)
                .build();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MapStyleOptions styleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style);
        mMap.setMapStyle(styleOptions);

        MarkerOptions HospitalGeneralOptions= new MarkerOptions()
                .position(HospitalGeneral)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .snippet("Hospital de Clinicas")

                .title("Atencion medica, emergencias, etc");

        MarkerOptions HospitalZonaSurOptions= new MarkerOptions()
                .position(HospitalZonSur)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .snippet("Hospital Zona Sur")
                .title("Atencion medica, emergencias, etc");

        MarkerOptions HospitalPortadaOptions= new MarkerOptions()
                .position(HospitalPortada)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .snippet("Hospital Municipal Portada")
                .title("Atencion medica, emergencias, etc");

        MarkerOptions HospitalLaPazOptions= new MarkerOptions()
                .position(HospitalLaPaz)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .snippet("Hospital La Paz")
                .title("Atencion medica, emergencias, etc");

        mMap.addMarker(HospitalGeneralOptions);
        mMap.addMarker(HospitalPortadaOptions);
        mMap.addMarker(HospitalLaPazOptions);
        mMap.addMarker(HospitalZonaSurOptions);


    }

    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // Tenemos permiso, podemos realizar la operación


    }}

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 777) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // El usuario dio permiso
            } else {
                // El usuario no dio permiso
            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }




}
