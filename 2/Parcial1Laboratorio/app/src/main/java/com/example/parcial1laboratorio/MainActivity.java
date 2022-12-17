package com.example.parcial1laboratorio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText user,pass;
    private String u,p;
    private Button btn1;
    private ProgressBar pgr1;

    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.txtuser);
        pass = (EditText) findViewById(R.id.txtclave);
        btn1 = (Button) findViewById(R.id.btningresar);
        pgr1 = (ProgressBar) findViewById(R.id.progressbar1);
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btningresar:
                new Task1().execute(user.getText().toString());
                break;
        }
    }

    class Task1 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() { //las instrucciones se ejecutan al momento de instanciar la clase
            pgr1.setVisibility(View.VISIBLE);
            btn1.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... strings) { //instrucciones que se ejecutan en segundo plano
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {//las instrucciones que se aplican una vez terminadas las ejecuciones en segundo plano
            ingresar();
        }
    }

    public void ingresar() {
        u = user.getText().toString();
        p = pass.getText().toString();

        if(u.equals("admin") && p.equals("admin")) {
            pgr1.setVisibility(View.INVISIBLE);
            btn1.setEnabled(true);
            Toast.makeText(getApplicationContext(), "Login exitoso", Toast.LENGTH_LONG).show();
            Intent obj = new Intent(MainActivity.this, Item_List.class);
            createNotificationChannel();
            createNotification();
            startActivity(obj);
        } else {
            Toast.makeText(getApplicationContext(), "Error volver a intentar", Toast.LENGTH_LONG).show();
            pgr1.setVisibility(View.INVISIBLE);
            btn1.setEnabled(true);
            user.setText("");
            pass.setText("");
            user.requestFocus();
        }
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Noticacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.login);
        builder.setContentTitle("Ingreso Exitoso");
        builder.setContentText("Bienvenido");
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }

}