package org.qtproject.example;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.*;

import android.widget.Toast;

import org.qtproject.example.DataFlairService;

import com.skydroid.android.usbserial.DeviceFilter;
import com.skydroid.android.usbserial.USBMonitor;
import com.skydroid.fpvlibrary.enums.PTZAction;
import com.skydroid.fpvlibrary.usbserial.UsbSerialConnection;
import com.skydroid.fpvlibrary.usbserial.UsbSerialControl;
import com.skydroid.fpvlibrary.utils.BusinessUtils;
import com.skydroid.fpvlibrary.video.FPVVideoClient;
import com.skydroid.fpvlibrary.widget.GLHttpVideoSurface;

import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import  android.util.Log;

public class VideoReceiveAndSendActivity extends Activity {

    private Context mContext;

    private FPVVideoClient mFPVVideoClient;

    private USBMonitor mUSBMonitor;

    private UsbDevice mUsbDevice;

   private VideoClient mVideoClient;

   private UsbSerialConnection mUsbSerialConnection;

   private UsbSerialControl mUsbSerialControl;

    //VideoProducer videoProducer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ForegroundServiceLauncher goingOnForever = ForegroundServiceLauncher.getInstance();
        //goingOnForever.startService(this);
        this.mContext = this;
        init();
        System.out.println("onCreate");
         //finish();
       }


    @Override
    public void onStart(){
        super.onStart();
        System.out.println("onStart");
//        if(videoProducer != null){
//            videoProducer.stop();
//            videoProducer = null;
//        }
        //videoProducer = new VideoProducer(this);
        //videoProducer.start();
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        System.out.println("onPause");
    }

    @Override
    public void onStop() {
        System.out.println("onStop Activity");
        super.onStop();
        //videoProducer.stop();
    }

    protected void onDestroy() {
        System.out.println("onDestroy Activity");
        super.onDestroy();
        disconnected();
              if(mUSBMonitor != null){
                  mUSBMonitor.unregister();
                  mUSBMonitor.destroy();
                  mUSBMonitor = null;
              }
        //videoProducer.destroy();

    }

private void init(){
        //初始化usb连接
        mUsbSerialConnection = new UsbSerialConnection(mContext);
        mUsbSerialConnection.setDelegate(new UsbSerialConnection.Delegate() {
            @Override
            public void onH264Received(byte[] bytes, int paySize) {
                //Log.d("MyApp", "h264 rcv");
                if(mFPVVideoClient != null){
                    mVideoClient.received(bytes,4,paySize);
                  //mFPVVideoClient.received(bytes,4,paySize);
                }
            }

            @Override
            public void onGPSReceived(byte[] bytes) {
                //GPS数据
            }

            @Override
            public void onDataReceived(byte[] bytes) {
                //数传数据
            }

            @Override
            public void onDebugReceived(byte[] bytes) {
                //遥控器数据
            }
        });

        mFPVVideoClient = new FPVVideoClient();
           mFPVVideoClient.setDelegate(new FPVVideoClient.Delegate() {
               @Override
               public void onStopRecordListener(String fileName) {
                   //停止录像回调
               }

               @Override
               public void onSnapshotListener(String fileName) {
                   //拍照回调
               }

               //视频相关
               @Override
               public void renderI420(byte[] frame, int width, int height) {
                   //mPreviewDualVideoView.renderI420(frame,width,height);
                   //System.out.println("here");
                   Log.d("MyApp", "renders");
               }

               @Override
               public void setVideoSize(int picWidth, int picHeight) {
                   //mPreviewDualVideoView.setVideoSize(picWidth,picHeight,mainHanlder);
               }

               @Override
               public void resetView() {
                   //mPreviewDualVideoView.resetView(mainHanlder);
               }
           });

        mVideoClient = new VideoClient();

        //FPV控制
        mUsbSerialControl = new UsbSerialControl(mUsbSerialConnection);

        mUSBMonitor = new USBMonitor(mContext,mOnDeviceConnectListener);
        List<DeviceFilter> deviceFilters = DeviceFilter.getDeviceFilters(mContext, R.xml.device_filter);
        mUSBMonitor.setDeviceFilter(deviceFilters);
        mUSBMonitor.register();
    }


//使用 USBMonitor 处理USB连接回调
    private USBMonitor.OnDeviceConnectListener mOnDeviceConnectListener = new USBMonitor.OnDeviceConnectListener() {
        // USB device attach
        // USB设备插入
        @Override
        public void onAttach(final UsbDevice device) {
            if(deviceHasConnected(device) || mUsbDevice != null){
                return;
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(device == null){
                            List<UsbDevice> devices = mUSBMonitor.getDeviceList();
                            if(devices.size() == 1){
                                mUSBMonitor.requestPermission(devices.get(0));
                            }
                        }else {
                            mUSBMonitor.requestPermission(device);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }

        // USB device detach
        // USB设备物理断开
        @Override
        public void onDettach(UsbDevice device) {
            if (!BusinessUtils.deviceIsUartVideoDevice(device)) {
                return;
            }
            if (!deviceHasConnected(device)) {
                return;
            }
            disconnected();
        }

        // USB device has obtained permission
        // USB设备获得权限
        @Override
        public void onConnect(UsbDevice device, USBMonitor.UsbControlBlock var2, boolean var3) {
            if (!BusinessUtils.deviceIsUartVideoDevice(device)) {
                return;
            }
            if (deviceHasConnected(device)) {
                return;
            }

            synchronized (this){
                if (BusinessUtils.deviceIsUartVideoDevice(device)) {
                    try {
                        //打开串口
                        mUsbSerialConnection.openConnection(device);
                        mUsbDevice = device;
                        //开始渲染视频
                        mFPVVideoClient.startPlayback();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }

        // USB device disconnected
        @Override
        public void onDisconnect(UsbDevice device, USBMonitor.UsbControlBlock var2) {
            if (!BusinessUtils.deviceIsUartVideoDevice(device)) {
                return;
            }
            if (!deviceHasConnected(device)) {
                return;
            }
            disconnected();
        }

        // USB device obtained permission failed
        @Override
        public void onCancel() {

        }
    };

    private void disconnected(){
        if(mUsbSerialConnection != null){
            try {
                mUsbSerialConnection.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(mFPVVideoClient != null){
            mFPVVideoClient.stopPlayback();
        }

        mUsbDevice = null;
    }

    private boolean deviceHasConnected(UsbDevice usbDevice){
        return usbDevice != null && usbDevice == mUsbDevice;
    }

}
