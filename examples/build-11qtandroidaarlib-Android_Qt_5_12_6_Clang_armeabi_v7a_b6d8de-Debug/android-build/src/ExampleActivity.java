package org.kressin.example;

//list of imports for UartVideo SDK
import com.shenyaocn.android.UartVideo.VideoClient;
import com.shenyaocn.android.UartVideo.usb.DeviceFilter;
import com.shenyaocn.android.UartVideo.usb.USBMonitor;
import android.hardware.usb.UsbDevice;
import java.util.List;
import android.os.Bundle;

import android.app.Activity;
import android.util.Log;

public class ExampleActivity extends org.qtproject.qt5.android.bindings.QtActivity
{

    private VideoClient video;
    private USBMonitor mUSBMonitor;
    private UsbDevice uartDevice;

    private static ExampleActivity activity_;

    public ExampleActivity()
    {
        activity_ = this;
    }

    public static void doSomething()
    {
        Log.d("Example", "ExampleActivity.doSomething: package=" + activity_.getPackageName());
    }

    private final USBMonitor.OnDeviceConnectListener mOnDeviceConnectListener = new USBMonitor.OnDeviceConnectListener() {
        // USB device attach
        @Override
        public void onAttach(final UsbDevice device) {

            if(deviceHasConnected(device) || uartDevice != null)
                return;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (device == null) {
                            final List<UsbDevice> devices = mUSBMonitor.getDeviceList();
                            if (devices.size() == 1) {
                                mUSBMonitor.requestPermission(devices.get(0));
                            }
                        } else {
                            mUSBMonitor.requestPermission(device);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }

        // USB device detach
        @Override
        public void onDettach(UsbDevice device) {
            if(!deviceIsUartVideoDevice(device)) {
                return;
            }

            if(!deviceHasConnected(device)) {
                return;
            }

            disconnected();
        }

        // Open video device
        // boolean VideoClient.openUsbSerial(UsbDevice device)
        // device: Usb serial device that has obtained permission. Refer to the following sample code for more info
        // return value, success on true, failure on false

        // USB device has obtained permission
        @Override
        public void onConnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock, boolean createNew) {
            if(!deviceIsUartVideoDevice(device)) {
                return;
            }
            if(deviceHasConnected(device)) {
                return;
            }

            synchronized (this) {
                if(deviceIsUartVideoDevice(device)) {
                    if(video.openUsbSerial(device))
                        uartDevice = device;
                }
            }
        }
        // USB device disconnected
        @Override
        public void onDisconnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock) {
            if(!deviceIsUartVideoDevice(device)) {
                return;
            }

            if(!deviceHasConnected(device)) {
                return;
            }

            disconnected();
        }
        // USB device obtained permission failed
        @Override
        public void onCancel() {

        }
    };

    private boolean deviceHasConnected(UsbDevice device) {
        return device != null && device.equals(uartDevice);
    }

    private static boolean deviceIsUartVideoDevice(UsbDevice device) {
        return device != null && (device.getVendorId() == 4292 && device.getProductId() == 60000);
    }

    private synchronized void disconnected() {
        video.stopPlayback();
        uartDevice = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    video = new VideoClient(this);

    video.setClientListener(new VideoClient.ClientListener() {

        @Override
        public void onH264Received(byte[] h264) {
            // Raw H.264 data callback
            Log.d("Example", "Raw H264 received!!!");
        }

        @Override
        public void onGPSReceived(byte[] bytes) {
            // GPS data callback
        }

        @Override
        public void onDataReceived(byte[] bytes) {
            // Transmission data callback
        }

        @Override
        public void onDebugReceived(byte[] bytes) {
            // Debug data callback
        }

        @Override
        public void onError(String error) {
            // Error data callback
        }
    });


    mUSBMonitor = new USBMonitor(this, mOnDeviceConnectListener);
    final List<DeviceFilter> filters = DeviceFilter.getDeviceFilters(this, R.xml.device_filter);
    mUSBMonitor.setDeviceFilter(filters);
    mUSBMonitor.register();
}

    @Override
    public void onDestroy() {
        super.onDestroy();

        disconnected();

        if (mUSBMonitor != null) {
            mUSBMonitor.unregister();
            mUSBMonitor.destroy();
            mUSBMonitor = null;
        }

}
}
