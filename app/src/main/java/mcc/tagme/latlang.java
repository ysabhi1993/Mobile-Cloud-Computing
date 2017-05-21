package mcc.tagme;

/**
 * Created by suryanarayana on 4/25/17.
 */


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static mcc.tagme.LoginActivity.test;


public class latlang extends AppCompatActivity {

    LocationManager locationManager;
    double longitudeBest, latitudeBest;
    double longitudeGPS, latitudeGPS;
    double longitudeNetwork, latitudeNetwork;
    TextView longitudeValueBest, latitudeValueBest;
    TextView longitudeValueGPS, latitudeValueGPS;
    TextView longitudeValueNetwork, latitudeValueNetwork;
    public static Double sharelongitudeBest;
    public static Double shareLatitudeBest;
    public static String shareIMEI;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latlang);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        longitudeValueBest = (TextView)  findViewById(R.id.longitudeValueBest);
        latitudeValueBest = (TextView) findViewById(R.id.latitudeValueBest);
        Log.i("hi","in latalng class");
         Button btn;  //to initiate the default gatheing of location data without any button press
        btn = (Button)findViewById(R.id.locationControllerBest);
        btn.performClick();
//       longitudeValueGPS = (TextView) findViewById(R.id.longitudeValueGPS);
//       latitudeValueGPS = (TextView) findViewById(R.id.latitudeValueGPS);
//        longitudeValueNetwork = (TextView) findViewById(R.id.longitudeValueNetwork);
//       latitudeValueNetwork = (TextView) findViewById(R.id.latitudeValueNetwork);



    }

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void toggleGPSUpdates(View view) {
        if (!checkLocation())
            return;
        Button button = (Button) view;
        if (button.getText().equals(getResources().getString(R.string.pause))) {
            locationManager.removeUpdates(locationListenerGPS);
            button.setText(R.string.resume);
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Log.i("hi","pressed mark location button ");
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 2 * 60 * 1000 , 4 , locationListenerGPS);
            Log.i("hi"," set text to pause location updates ");
            button.setText(R.string.pause);
        }
    }

    public void toggleBestUpdates(View view) {
        if (!checkLocation())
            return;
        Button button = (Button) view;
        if (button.getText().equals(getResources().getString(R.string.pause))) {
            locationManager.removeUpdates(locationListenerBest);
            button.setText(R.string.resume);
            TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            final String imei_val =  telephonyManager.getDeviceId();

            final TextView imeiview = (TextView) findViewById(R.id.imei);
            imeiview.setText("IMEI NO - " + imei_val);
        } else {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            String provider = locationManager.getBestProvider(criteria, true);
            if (provider != null) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(provider, 2 * 60 * 1000 , 4, locationListenerBest);
                button.setText(R.string.pause);
                Toast.makeText(this, "Best Provider is " + provider, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void toggleNetworkUpdates(View view) {
        if (!checkLocation())
            return;
        Button button = (Button) view;
        if (button.getText().equals(getResources().getString(R.string.pause))) {
            locationManager.removeUpdates(locationListenerNetwork);
            button.setText(R.string.resume);
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 2* 60* 1000 , 4, locationListenerNetwork);
            Toast.makeText(this, "Network provider started running", Toast.LENGTH_LONG).show();
            button.setText(R.string.pause);
        }
    }

    private final LocationListener locationListenerBest = new LocationListener() {
        public void onLocationChanged(Location location) {
            TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            final String imei_val =  telephonyManager.getDeviceId();
            final TextView imeiview = (TextView) findViewById(R.id.imei);
            shareIMEI = imei_val;



            longitudeBest = location.getLongitude();
            latitudeBest = location.getLatitude();

            shareLatitudeBest = latitudeBest;
            sharelongitudeBest = longitudeBest;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    longitudeValueBest.setText(longitudeBest + "");
                    latitudeValueBest.setText(latitudeBest + "");
                    imeiview.setText("IMEI NO - " + imei_val);

                    Log.i("hi","updating the location");
                    Toast.makeText(latlang.this, "Best Provider update", Toast.LENGTH_SHORT).show();



//                        Intent myIntent = new Intent(latlang.this, latlang2.class);
////
//                        latlang.this.startActivity(myIntent);

                    Intent intent = new Intent(latlang.this, database.class);
                    startService(intent);






                }
            });





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

    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    longitudeValueBest.setText(longitudeNetwork + "");
                    latitudeValueBest.setText(latitudeNetwork + "");
                    Toast.makeText(latlang.this, "Network Provider update", Toast.LENGTH_SHORT).show();
                }
            });
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

    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    longitudeValueBest.setText(longitudeGPS + "");
                    latitudeValueBest.setText(latitudeGPS + "");
                    Toast.makeText(latlang.this, "GPS Provider update", Toast.LENGTH_SHORT).show();
                }
            });
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



}
