package com.example.proximity_sensor;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidProximitySensorActivity extends Activity {

    TextView textView;
    Button sifirla;
    int sayac;

    SensorManager mySensorManager;
    Sensor myProximitySensor;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Ekran sadece dikey konumda
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Toast.makeText(AndroidProximitySensorActivity.this, "Şınav çekerken burnunuzu hoparlöre dokundurunuz. Aksi" +
                " takdirde sensör devreye girmeyecektir.", Toast.LENGTH_LONG)
                .show();

        textView = (TextView)findViewById(R.id.sayac);
        sifirla = (Button)findViewById(R.id.sifirla_buton);

        sifirla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("0");
                sayac = 0;
            }
        });

        mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        // Test where phone has or has not a proximity sensor
        if (myProximitySensor == null){



        }
        // Sensor varsa
        else {


            mySensorManager.registerListener(proximitySensorEventListener,myProximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
            sayac = 0;

        }
    }




    SensorEventListener proximitySensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) { //sensor değeri değiştiğinde


            //sensor tipi proximity(yakınlık) ise çalış
            if(sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY){


                /**
                 * Maksimum uzaklık degerinden küçükse sayacı 1 arttır.
                 * Yani en yakın oldugu durumda çalışır. Uzaklaşırken çalışmaz.
                 */
                if (sensorEvent.values[0] < sensorEvent.sensor.getMaximumRange()) {
                    sayac++;
                    if(sayac > 0 && sayac < 3){ //1,2
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.comeon1).start();

                    }
                    if(sayac == 3){ //3
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.comeon1).stop();
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.comeyoucandoit).start();

                    }
                    if(sayac > 3 && sayac < 6){ //4,5
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.comeyoucandoit).stop();
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.comeon1).start();

                    }
                    if(sayac > 5 && sayac <8){ //6,7
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.comeon1).stop();
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yougotit).start();

                    }
                    if(sayac > 7 && sayac <11){ //8,10
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yougotit).stop();
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yougotit).start();

                    }
                    if(sayac > 10 && sayac <14){ //11,13
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yougotit).stop();
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yes).start();

                    }
                    if(sayac == 14){ //14
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yes).stop();
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yes).start();

                    }
                    if(sayac > 14 && sayac <18){ //15,17
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yes).stop();
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yes2).start();

                    }
                    if(sayac == 18){ //18
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yes2).stop();
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yes3).start();

                    }
                    if(sayac == 19){ //19
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yes3).stop();
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yes4).start();
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yes4).stop();
                    }
                    if(sayac == 20){ //19
                        MediaPlayer.create(AndroidProximitySensorActivity.this, R.raw.yes4).stop();
                    }


                }


                textView.setText(new Integer(sayac).toString());
                //textView.setText(new Integer(sayac).toString());

                //TODO UI Tasarlanacak


            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };



}
