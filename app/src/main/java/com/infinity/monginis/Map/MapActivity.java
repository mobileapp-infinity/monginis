package com.infinity.monginis.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.infinity.monginis.R;
import com.infinity.monginis.custom_class.TextViewRegularFont;
import com.infinity.monginis.dashboard.activity.DashboardActivity;
import com.infinity.monginis.utils.CommonUtil;
import com.infinity.monginis.utils.IntentConstants;
import com.infinity.monginis.utils.MySharedPreferences;

import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener, View.OnClickListener {

    Intent mapIntent;
    double latitude;
    double longitude;
    GoogleMap googleMap;
    TextViewRegularFont tvSetLocation;
    LinearLayout llAddress;
    LatLng markerLatLng;
    TextViewRegularFont tvAddress;
    String userCurrentStreetName, userCurrentCityName, userCurrentAddress;
    MySharedPreferences mySharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initView();
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(MapActivity.this);
        llAddress = findViewById(R.id.llAddress);
        tvAddress = findViewById(R.id.tvAddress);

        MapsInitializer.initialize(MapActivity.this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.viewLocationMap);
        tvSetLocation = findViewById(R.id.tvSetLocation);
        tvSetLocation.setOnClickListener(this);

        mapFragment.getMapAsync(this);
        mapIntent = getIntent();
        latitude = mapIntent.getDoubleExtra(IntentConstants.USER_CURRENT_LATITUDE, 0.0);
        longitude = mapIntent.getDoubleExtra(IntentConstants.USER_CURRENT_LONGITUDE, 0.0);
        mySharedPreferences.setLatLong(latitude,longitude);

        System.out.println("latitude longitude From Fetched location " + latitude + "----------" + longitude);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMyLocationEnabled(true);

        if (!CommonUtil.checkIsEmptyOrNullCommon(latitude) && !CommonUtil.checkIsEmptyOrNullCommon(longitude)) {
            LatLng latLng = new LatLng(latitude, longitude);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvAddress.setText(getAddressFromLatLng(latitude, longitude));
                }
            });

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng).zoom(10).build();


            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));


            googleMap.addMarker(new MarkerOptions().draggable(true)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                    .position(latLng)
                    .zIndex(20));


            googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) {
                    Animation bottomDown = AnimationUtils.loadAnimation(MapActivity.this,
                            R.anim.bottom_down);

                    llAddress.startAnimation(bottomDown);
                    llAddress.setVisibility(View.GONE);
                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {
                    Double latitude = marker.getPosition().latitude;
                    Double longitude = marker.getPosition().longitude;

                    markerLatLng = new LatLng(latitude, longitude);
                    mySharedPreferences.setLatLong(latitude,longitude);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvAddress.setText(getAddressFromLatLng(latitude, longitude));
                        }
                    });
                    llAddress.setVisibility(View.VISIBLE);
                    Animation bottomDown = AnimationUtils.loadAnimation(MapActivity.this,
                            R.anim.bottom_up);

                    llAddress.startAnimation(bottomDown);

                }
            });
        } else {
            Toast.makeText(MapActivity.this, "Please Try Again Later", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    public String getAddressFromLatLng(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
        String streetName = null;
        try {

            Address address = geocoder.getFromLocation(latitude, longitude, 1).get(0);

            streetName = address.getAddressLine(0);
            //userCurrentCityName = cityName;

            userCurrentCityName = getCityNameFromLatLong(latitude, longitude);
            if (!CommonUtil.checkIsEmptyOrNullCommon(address.getAddressLine(0))) {
                userCurrentAddress = address.getAddressLine(0);
            }
            if (!CommonUtil.checkIsEmptyOrNullCommon(address.getThoroughfare())) {
                userCurrentStreetName = address.getThoroughfare();
            } else if (!CommonUtil.checkIsEmptyOrNullCommon(address.getSubThoroughfare())) {
                userCurrentStreetName = address.getSubThoroughfare();
            }
        } catch (Exception ex) {
            System.out.println(ex);

            streetName = null;
        }

        return streetName;
    }

    private void redirectToDashboard() {
        Intent intent = new Intent(MapActivity.this, DashboardActivity.class);
        intent.putExtra(IntentConstants.USER_CURRENT_STREET_NAME, userCurrentStreetName);
        intent.putExtra(IntentConstants.USER_CURRENT_ADDRESS, userCurrentAddress);
        intent.putExtra(IntentConstants.USER_CURRENT_CITY_NAME, userCurrentCityName);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.tvSetLocation) {
            redirectToDashboard();
        }

    }

    public String getCityNameFromLatLong(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
        String cityName = null;
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            cityName = addresses.get(0).getLocality();
        } catch (Exception ex) {
            cityName = null;
        }
        return cityName;
    }
}
