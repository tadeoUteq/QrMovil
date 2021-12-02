package com.example.qrotuberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class SesionActivity extends AppCompatActivity {
    private String correo;
    private TextView emailUsuario;
    //creacion de variables para las notificaciones

    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        correo = getIntent().getStringExtra("correo"); // Se obtiene el nombre del jugador, debe contener el mismo texto que el de .putExtra de vista anterior
        emailUsuario = findViewById(R.id.emailUsuario); // se inicializa la vista
        emailUsuario.setText("" + correo); // se inserta el nombre del jugador en la vista
        Integer pruebaContador = 2;

        if(pruebaContador == 2) {
            createNotificationChannel();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);

            NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();

            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("Ya has logrado obtener");
            builder.setContentText("4 suscriptores!"); // mandarle la variable de n usuarios
            builder.setColor(Color.RED);
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            builder.setLights(Color.MAGENTA, 1000, 1000);
            builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
            builder.setDefaults(Notification.DEFAULT_SOUND);
            builder.extend(wearableExtender);

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
            notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());

            Intent intent = new Intent(this, SesionActivity.class).putExtra("1", pruebaContador);

            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(pendingIntent);

            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.notify(NOTIFICACION_ID, builder.build());
        } else {

        }

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Noticacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}