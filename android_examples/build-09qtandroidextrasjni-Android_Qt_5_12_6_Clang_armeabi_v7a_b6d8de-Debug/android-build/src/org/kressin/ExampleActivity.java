package org.kressin.example;


import com.shenyaocn.android.OpenH264.Decoder;
//import com.shenyaocn.android.UartVideo.VideoClient;
//import com.shenyaocn.android.UartVideo.usb.DeviceFilter;
//import com.shenyaocn.android.UartVideo.usb.USBMonitor;

import android.app.Activity;
import android.util.Log;

public class ExampleActivity extends org.qtproject.qt5.android.bindings.QtActivity
{
//    private VideoClient video;
//    private USBMonitor mUSBMonitor;
//    private UsbDevice uartDevice;


    private static ExampleActivity activity_;

    public ExampleActivity()
    {
        activity_ = this;
    }

    public static void doSomething()
    {
        Log.d("Example", "ExampleActivity.doSomething: package=" + activity_.getPackageName());
    }
}
