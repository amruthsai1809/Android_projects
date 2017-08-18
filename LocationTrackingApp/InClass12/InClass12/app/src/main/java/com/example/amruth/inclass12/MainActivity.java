package com.example.amruth.inclass12;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    LocationManager manager;
    GoogleMap mGoogleMap;
    PolylineOptions polylineOptions;
    LocationListener listener;
    Marker marker1, marker2;
    int flag=3,longclick=0;
    ArrayList<LatLng> latLngs = new ArrayList<LatLng>();
    LatLng startlatlng,endlatng,upadatedlanglat;
    LatLng sw,ne;
    Polyline line;
    int startTracking=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (googleServicesAvailable()) {
            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                setContentView(R.layout.main);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("GPS not enabled")
                        .setMessage("would you like to enable gps settings?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(intent);

                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        finish();

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            } else {
                polylineOptions=new PolylineOptions();
                setContentView(R.layout.main);
                Toast.makeText(MainActivity.this, "perfect", Toast.LENGTH_LONG).show();
                // get the map object by using the map fragment
                getMapObject();
            }
        } else {
            Toast.makeText(MainActivity.this, "google services not available", Toast.LENGTH_LONG).show();
        }
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                upadatedlanglat=new LatLng(location.getLatitude(),location.getLongitude());
                if (startTracking == 1){
                   if(line!=null){
                       line.remove();
                        line=null;
                   }

                    polylineOptions.add(new LatLng(location.getLatitude(),location.getLongitude()));
                    line=mGoogleMap.addPolyline(polylineOptions);


                    //sunil
//                    private final LatLngBounds bounds = new LatLngBounds()



//                    latLngs.add(new LatLng(location.getLatitude(),location.getLongitude()));
//                    if(line!=null){
//                        line.remove();
//                    }else {
//                        Log.d("demoo","dfsdfdsfdsf");
//                        for(int i=0;i<latLngs.size();i++){
//                            polylineOptions.add(latLngs.get(i));
//
//
//                        }
//                    }
//                    line=mGoogleMap.addPolyline(polylineOptions);



                }

//
//                    if(flag==3){
//                        LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
//                        startlatlng=ll;
//                    }else if(flag==1) {
//                        LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
//                        endlatng=ll;
//
//                    }else {
//                        if(flag==2){
//                            polylineOptions.add(startlatlng).add(new LatLng(location.getLatitude(),location.getLongitude()));
//                            line=mGoogleMap.addPolyline(polylineOptions);
//                        }
//
//
//
//
//                    }



                Log.d("demo", "latitude - " + location.getLatitude() + " , " + "longitude - " + location.getLongitude());

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("GPS not enabled")
                    .setMessage("would you like to enable gps settings?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);

                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    finish();

                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
    }

    private void removeEverything(){
        marker1.remove();
        marker1=null;
        marker2.remove();
        marker2=null;
        line.remove();
        line=null;
        //line.remove();
        //circle.remove();
        //circle=null;

    }


    private void getMapObject() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

    }

    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "cant connect", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);
        UiSettings uiSettings=mGoogleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        mGoogleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                if(flag==3){
                    startlatlng=upadatedlanglat;
                    //sunil
                    sw = startlatlng;
                    ne = new LatLng(startlatlng.latitude+0.00000150, startlatlng.longitude+0.00000150);
                   // LatLngBounds.builder(LatLng);


                    polylineOptions=new PolylineOptions();
                    //latLngs.add(startlatlng);
                    polylineOptions.add(startlatlng);
                    startTracking = 1;

                    flag=1;
                    MarkerOptions options=new MarkerOptions()
                            .snippet("here i am")
                            .position(startlatlng);
                    if(marker1==null){
                        marker1=mGoogleMap.addMarker(options);

                    }else if(marker2==null){
                        marker2=mGoogleMap.addMarker(options);
                        //drawline();
                    }else {
                        removeEverything();
                        marker1=mGoogleMap.addMarker(options);
                    }


                }else {
                    endlatng=upadatedlanglat;
                    startTracking = 0;
                    flag=3;
                    MarkerOptions options=new MarkerOptions()
                            .snippet("here i am")
                            .position(endlatng);
                    if(marker1==null){
                        marker1=mGoogleMap.addMarker(options);

                    }else if(marker2==null){
                        marker2=mGoogleMap.addMarker(options);
                        //drawline();
                    }else {
                        removeEverything();
                        marker1=mGoogleMap.addMarker(options);
                    }


                }






            }
        });
    }
}
