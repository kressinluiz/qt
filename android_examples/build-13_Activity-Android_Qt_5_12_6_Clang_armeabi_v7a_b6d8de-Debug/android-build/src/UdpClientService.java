package org.qtproject.example;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Build;
import android.os.IBinder;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.qtproject.example.UdpClientSendThread;

import java.io.*;

import androidx.core.app.NotificationCompat;

import android.util.Log;
import android.widget.Toast;

import com.shenyaocn.android.UartVideo.VideoClient;
import com.shenyaocn.android.UartVideo.usb.DeviceFilter;
import com.shenyaocn.android.UartVideo.usb.USBMonitor;

import java.util.List;

import static com.shenyaocn.android.UartVideo.VideoClient.Sizes.Size_HD;
import static com.shenyaocn.android.UartVideo.VideoClient.Sizes.Size_Smooth;

public class UdpClientService extends Service{

    String ACTION_STOP_SERVICE= "STOP";
    private static final String NOTIFICATION_CHANNEL_ID ="notification_channel_id";
    private static final String NOTIFICATION_Service_CHANNEL_ID = "service_channel";

    public BlockingQueue<byte[]> packetsReceivedQueue = new ArrayBlockingQueue<byte[]>(24000);

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {

        System.out.println("UdpClientService Creating BABYE");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (ACTION_STOP_SERVICE.equals(intent.getAction())) {
            Log.d("ForegroundService","called to cancel service");
            stopSelf();
        }

        Intent stopSelf = new Intent(this, DataFlairService.class);
        stopSelf.setAction(ACTION_STOP_SERVICE);

        PendingIntent pStopSelf = PendingIntent
                .getService(this, 0, stopSelf
                        ,PendingIntent.FLAG_CANCEL_CURRENT);

        int icon = R.drawable.vigiair_logo;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            icon = R.drawable.vigiair_logo;
        }

        Intent notificationIntent = new Intent(this, VideoReceiverActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(icon)
                .setContentIntent(pendingIntent)
                .setContentTitle("UDP Client sending...")
                .setContentText("Running...")
                .addAction(R.drawable.vigiair_logo, "STOP", pStopSelf);
        Notification notification=builder.build();

        if(Build.VERSION.SDK_INT>=26) {

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_Service_CHANNEL_ID, "Sync Service", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Service Name");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);

            notification = new Notification.Builder(this,NOTIFICATION_Service_CHANNEL_ID)
                    .setContentTitle("UDP Client sending...")
                    //.setContentText("Running...")
                    .setSmallIcon(icon)
                    .setContentIntent(pendingIntent)
                    .addAction(R.drawable.vigiair_logo, "STOP", pStopSelf)
                    .build();
        }

        startForeground(121, notification);

        System.out.println("Iniciando Stream UDP!");
        UdpClientSendThread c = new UdpClientSendThread(packetsReceivedQueue);
        new Thread(c).start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}

