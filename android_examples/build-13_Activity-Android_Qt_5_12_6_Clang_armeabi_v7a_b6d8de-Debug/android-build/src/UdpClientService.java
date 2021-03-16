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

import org.qtproject.example.BlockingQueueVideoPackets;

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

        System.out.println("Iniciando Stream UDP!");

        try{
            BlockingQueueVideoPackets.main(null);
        } catch(Exception e){
            System.out.println("BlockingQueueVideoPackets: main exception");
        }
        //UdpClientSendThread c = new UdpClientSendThread(packetsReceivedQueue);
        //new Thread(c).start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}

