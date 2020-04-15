package com.example.sarahnadi4.sensor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindSensor extends Activity implements SensorEventListener {
    private SensorManager sensorManager; //Initiating SensorManager
    private String [] sensorName;
    private PopupWindow mPopupWindow; //Initiating Popup window variable
    private Context mContext;  // Context for the popup window
    private Activity mActivity;
    private RelativeLayout mRelativeLayout;
    String sensor_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_sensor);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> mySensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        final ListView list = (ListView) findViewById(R.id.list_item);
        List<String> myArrayList=new ArrayList<>();
        mContext = getApplicationContext();
        mActivity = FindSensor.this;
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);



        if(mySensors.size()>0) {
            for (Iterator<Sensor> it = mySensors.iterator(); it.hasNext(); ) {   //Display the sensors one by one.
                Sensor sensor = it.next(); // get next sensor
                myArrayList.add(sensorTypeToString(sensor.getType())); // add on the array list
                Log.v("Found Sensor",sensor.getName()); // log on verbose if we are getting sensor names or not

            }
            Log.v("Found Array Sensor",myArrayList.toString());
            // Setting up array adapter now
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    R.layout.row_item,R.id.sensorName,
                    myArrayList );

            list.setAdapter(arrayAdapter); // show on the screen(ListView)




        }else{
            Toast toast = Toast.makeText(this, "No Sensors to Display ", Toast.LENGTH_LONG);
            toast.show();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.custom_layout,null);

                sensor_name = (String) list.getItemAtPosition(position);
                Toast toast2 = Toast.makeText(FindSensor.this, sensor_name, Toast.LENGTH_LONG);
                toast2.show();

                Intent intent = new Intent(FindSensor.this, ShowValue.class);
                intent.putExtra("sensor", sensor_name);
                startActivity(intent);






            }
        });
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
                    textShow = textShow + "Data Not Available.....";
            }


            Toast toast = Toast.makeText(this, textShow, Toast.LENGTH_LONG);
            toast.show(); // setting up the value on the popup window
        }else{
            float[] sensorValues = event.values;
            String textShow = sensorNew;
            for(float v:sensorValues){
                if (v != 0) {
                    textShow = textShow + "\n" + v;
                }else
                    textShow = textShow + "Data Not Available.....";
            }

            Toast toast = Toast.makeText(this, textShow, Toast.LENGTH_LONG);
            toast.show(); // setting up the value on the popup window // Setting Text on POPup window
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
    @Override
    public void onBackPressed() { // restart the application on back press

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}

