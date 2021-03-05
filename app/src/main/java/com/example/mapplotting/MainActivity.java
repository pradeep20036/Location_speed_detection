package com.example.mapplotting;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {


    private Button bt_location;
    private TextView tv_location;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);

        bt_location=findViewById(R.id.bt_getlocation);
        tv_location=findViewById(R.id.tv_location);

        bt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

                    if(getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    ==PackageManager.PERMISSION_GRANTED){

                        //get the location in here
                        fusedLocationProviderClient.getLastLocation()
                                .addOnSuccessListener(new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        tv_location.setText(location.getSpeed()+"");
                                    }
                                });
                    }
                    else
                    {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                    }

                }
            }
        });



    }
}
