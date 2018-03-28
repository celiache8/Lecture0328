package com.example.celiachen.lecture0328;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by celiachen on 3/28/18.
 */

public class ProximityActivity extends AppCompatActivity {
    // sensor
    // sensorManager
    // each sensor has a code
    // if you want to access proximity sensor
    // use the code that represents proximity sensor

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        // initialize sensor manager
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        // get the proximity sensor
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (proximitySensor == null){
            finish();
        }
        // override the event listener to get data
        proximitySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // when data changes, what do you do?
                // if the proximity is less than the maximum range
                // change the background color to blue
                // otherwise, the background will be green

                if (sensorEvent.values[0] < proximitySensor.getMaximumRange()){
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }
                else{
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }

    // LIFE CYCLE OF ANDROID activities
    // onCreate
    // onResume
    // onDestroy
    // onPause

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(proximitySensorListener, proximitySensor,
                2*1000); // registering the sensor listener again
        // to gather data
    }

    @Override
    protected void onPause(){
        super.onPause();
        // unregister your sensor in onpause
        sensorManager.unregisterListener(proximitySensorListener);
    }

}
