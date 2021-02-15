package org.kressin.example;

import org.kressin.example.Main;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

public class Notifier extends Main
{
    private NotificationManager m_notificationManager;
    private Notification.Builder m_builder;

    public void notify(String s)
    {
        if (m_notificationManager == null)
        {
            m_notificationManager = (NotificationManager)activity.getSystemService(Context.NOTIFICATION_SERVICE);
            m_builder = new Notification.Builder(activity);

            //m_builder.setSmallIcon(R.drawable.icon);

            String app = activity.getPackageName();
            app = app.substring(app.lastIndexOf(".")+1);
            m_builder.setContentTitle(app+":");
        }

        m_builder.setContentText(s);
        m_notificationManager.notify(1, m_builder.build());
    }

    public void cancel()
    {
        if (m_notificationManager != null)
        {
           m_notificationManager.cancelAll();
        }
    }
}
