package org.qtproject.example;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import java.io.*;

public class ForegroundServiceLauncher
{
    private static volatile ForegroundServiceLauncher foregroundServiceLauncher;

    public static ForegroundServiceLauncher getInstance()
    {
        if (foregroundServiceLauncher == null)
        {
            synchronized (ForegroundServiceLauncher.class)
            {
                if (foregroundServiceLauncher == null)
                {
                    foregroundServiceLauncher = new ForegroundServiceLauncher();
                }
            }
        }
        return foregroundServiceLauncher;
    }

    private ForegroundServiceLauncher()
    {
        //Prevent form the reflection api.
        if (foregroundServiceLauncher != null)
        {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public synchronized void startService(Context context)
    {
         System.out.println("ForegroundServiceLauncher: startService");
        if (!isServiceRunning(context))
        {
            if (isOreoOrHigher())
            {
                System.out.println("DataFlairService: startService");
                context.startForegroundService(new Intent(context, DataFlairService.class));
                System.out.println("UdpClientService: startService");
                //context.startForegroundService(new Intent(context, UdpClientService.class));
            }
            else
            {
                context.startService(new Intent(context,DataFlairService.class));
                context.startService(new Intent(context,UdpClientService.class));
            }
        }

    }

    public synchronized void stopService(Context context)
    {
        System.out.println("stopService");
        if (isOreoOrHigher())
        {
            //System.out.println("DataFlairService: startService");
            //context.startForegroundService(new Intent(context, DataFlairService.class));
            //System.out.println("UdpClientService: stopService");
            //Intent stopIntent = new Intent(context, UdpClientService.class);
            //stopIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
            //context.startForegroundService(stopIntent);
        }
        else
        {
            System.out.println("Shouldnt get here");
            //context.startService(new Intent(context,DataFlairService.class));
            //context.startService(new Intent(context,UdpClientService.class));
        }
    }

    private boolean isOreoOrHigher()
    {
        return true;
    }

    private boolean isServiceRunning(Context ctx) {

        ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (DataFlairService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
