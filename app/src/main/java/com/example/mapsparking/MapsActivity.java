package com.example.mapsparking;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mapsparking.Controller.DatabaseHandler;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mapsparking.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    DatabaseHandler db;

    private LatLng Parking_Bordet = new LatLng(50.87762757467018, 4.411836140248157);
    private LatLng Parking_Tilleul = new LatLng(50.87110924274438, 4.3960856798539725);
    private LatLng Parking_Ceria = new LatLng(50.8159457230718, 4.288435421577517);
    private LatLng Parking_Hermann = new LatLng(50.812655068316644, 4.43123126551602);

    private Marker mParking_Bordet;
    private Marker mParking_Tilleul;
    private Marker mParking_Ceria;
    private Marker mParking_Hermann;

    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //binding = ActivityMapsBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
          //      .findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        db = new DatabaseHandler(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);


        List<Marker> markerList = new ArrayList<>();

        mParking_Bordet = mMap.addMarker(new MarkerOptions()
                .position(Parking_Bordet).title("Parking Bordet")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mParking_Bordet.setTag(0);
        markerList.add(mParking_Bordet);

        mParking_Tilleul = mMap.addMarker(new MarkerOptions()
                .position(Parking_Tilleul).title("Parking Tilleul")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mParking_Tilleul.setTag(0);
        markerList.add(mParking_Tilleul);

        mParking_Ceria = mMap.addMarker(new MarkerOptions()
                .position(Parking_Ceria).title("Parking Ceria")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mParking_Ceria.setTag(0);
        markerList.add(mParking_Ceria);

        mParking_Hermann = mMap.addMarker(new MarkerOptions()
                .position(Parking_Hermann).title("Parking Hermann")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mParking_Hermann.setTag(0);
        markerList.add(mParking_Hermann);

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setOnMarkerClickListener(this);

        for(Marker m : markerList){
            LatLng latLng = new LatLng(m.getPosition().latitude,m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this,marker.getPosition().toString(),Toast.LENGTH_SHORT).show();
        return false;
    }
    @Override
    public void onMapLongClick(LatLng latLng) {
//        mMap.addMarker(new MarkerOptions().position(latLng).title("new Marker")
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        Intent intent = new Intent(MapsActivity.this, AddActivity.class);
        intent.putExtra("latitude" , latLng.latitude);
        intent.putExtra("longitude" , latLng.longitude);

        startActivity(intent);
    }
}