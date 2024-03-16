package com.example.objectdetectornearthescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView output;
    private SensorManager sensorManager;
    private Sensor sensorProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            Sensor multiSensor = sensorEvent.sensor;

            if (multiSensor.getType() == Sensor.TYPE_PROXIMITY) {
                output.setText("Расстояние до приближающегося объекта " + sensorEvent.values[0] + "см");
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, sensorProximity, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}