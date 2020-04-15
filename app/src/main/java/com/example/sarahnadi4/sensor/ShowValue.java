package com.example.sarahnadi4.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sarah on 20/10/2018.
 */

public class ShowValue extends Activity implements SensorEventListener {
    private SensorManager sensorManager; //Initiating SensorManager
    private String sensor_name;
    TextView values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showvalue);
        values = (TextView) findViewById(R.id.values);
        sensor_name= getIntent().getExtras().getString("sensor");

        if(sensor_name.equals("Light"))//working
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            sensorManager.registerListener(ShowValue.this,light,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if (sensor_name.equals("Game Rotation Vector")) //working
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor gameRoataion = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
            sensorManager.registerListener(ShowValue.this,gameRoataion, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if (sensor_name.equals("Magnetic Field"))//working
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor magnetic1 = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            sensorManager.registerListener(ShowValue.this,magnetic1,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensor_name.equals(" Magnetic Field Uncalibrated"))// working
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor magnetic2 = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
            sensorManager.registerListener(ShowValue.this,magnetic2,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensor_name.equals("Proximity"))//working
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            sensorManager.registerListener(ShowValue.this,proximity,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensor_name.equals("Gravity"))
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            sensorManager.registerListener(ShowValue.this,gravity,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensor_name.equals("Linear Acceleration"))// crashes
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor linear = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            sensorManager.registerListener(ShowValue.this,linear,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensor_name.equals("Rotation Vector")) // Crashes
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor rotation = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            sensorManager.registerListener(ShowValue.this,rotation,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensor_name.equals("Step Detector"))
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor detector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            sensorManager.registerListener(ShowValue.this,detector,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensor_name.equals("Step Counter"))//working
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor counter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            sensorManager.registerListener(ShowValue.this,counter,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensor_name.equals("Significant Motion"))
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor motion = sensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
            sensorManager.registerListener(ShowValue.this,motion,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensor_name.equals("Geomagnetic Rotation Vector")) // working
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor georotation = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
            sensorManager.registerListener(ShowValue.this,georotation,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensor_name.equals("Orientation")) //working
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
            sensorManager.registerListener(ShowValue.this,orientation,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensor_name.equals("Gyroscope"))
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor amd = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            sensorManager.registerListener(ShowValue.this,amd,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensor_name.equals("Accelerometer"))
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor pedometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(ShowValue.this,pedometer,SensorManager.SENSOR_DELAY_NORMAL);
        }else if(sensor_name.equals("Gyroscope Uncalibrated"))
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor pedometer = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
            sensorManager.registerListener(ShowValue.this,pedometer,SensorManager.SENSOR_DELAY_NORMAL);}
        else if(sensor_name.equals("Magnetic Field Uncalibrated"))
        {
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            Sensor pedometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
            sensorManager.registerListener(ShowValue.this,pedometer,SensorManager.SENSOR_DELAY_NORMAL);}
        else{
            Toast toast3 = Toast.makeText(ShowValue.this, "Error with " + sensor_name + " Sensor", Toast.LENGTH_LONG);
            toast3.show();

        }

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorName ;
        sensorName = event.sensor.getType();
        String sensorNew = sensorTypeToString(sensorName);
        if( sensorNew != sensor_name ){ // Check for the name and send the Proper name of the sensor
            sensorNew = sensor_name;
            float[] sensorValues = event.values; // getting the evnt value
            String textShow = sensorNew;
            for(float v:sensorValues){
                if (v != 0) {
                    textShow = textShow + "\n" + v;
                }else
                    textShow = textShow + " ";
            }

            values.setText(textShow);

        }else{
            float[] sensorValues = event.values;
            String textShow = sensorNew;
            for(float v:sensorValues){
                if (v != 0) {
                    textShow = textShow + "\n" + v;
                }else
                    textShow = textShow + " ";
            }

            values.setText(textShow);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public static String sensorTypeToString(int sensorType) {  // Giving Sensors proper name by getting their type
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                return "Accelerometer";
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
            case Sensor.TYPE_TEMPERATURE:
                return "Ambient Temperature";
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                return "Game Rotation Vector";
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                return "Geomagnetic Rotation Vector";
            case Sensor.TYPE_GRAVITY:
                return "Gravity";
            case Sensor.TYPE_GYROSCOPE:
                return "Gyroscope";
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                return "Gyroscope Uncalibrated";
            case Sensor.TYPE_HEART_RATE:
                return "Heart Rate Monitor";
            case Sensor.TYPE_LIGHT:
                return "Light";
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return "Linear Acceleration";
            case Sensor.TYPE_MAGNETIC_FIELD:
                return "Magnetic Field";
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                return "Magnetic Field Uncalibrated";
            case Sensor.TYPE_ORIENTATION:
                return "Orientation";
            case Sensor.TYPE_PRESSURE:
                return "Orientation";
            case Sensor.TYPE_PROXIMITY:
                return "Proximity";
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return "Relative Humidity";
            case Sensor.TYPE_ROTATION_VECTOR:
                return "Rotation Vector";
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                return "Significant Motion";
            case Sensor.TYPE_STEP_COUNTER:
                return "Step Counter";
            case Sensor.TYPE_STEP_DETECTOR:
                return "Step Detector";
            default:
                return "Unknown Sensor";
        }   }
}