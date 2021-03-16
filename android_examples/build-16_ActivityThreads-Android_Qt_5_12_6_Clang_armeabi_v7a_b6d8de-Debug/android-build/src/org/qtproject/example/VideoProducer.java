//package org.qtproject.example;

//import android.util.Log;

////VideoClient
//import com.shenyaocn.android.UartVideo.VideoClient;
//import com.shenyaocn.android.UartVideo.usb.DeviceFilter;
//import com.shenyaocn.android.UartVideo.usb.USBMonitor;
//import android.hardware.usb.UsbDevice;
//import android.content.Context;
//import java.util.List;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

//public class VideoProducer {

//    //VideoClient
//    private VideoClient video;
//    private USBMonitor mUSBMonitor;
//    private UsbDevice uartDevice;
//    private Context context;
//    private ExecutorService threadPool = Executors.newCachedThreadPool();
//    private BlockingQueue<byte[]> filaPacotesVideo = new ArrayBlockingQueue<byte[]>(200, true);

//    public VideoProducer(Context context){
//        this.context = context;
//    }

//    private boolean deviceHasConnected(UsbDevice device) {
//        return device != null && device.equals(uartDevice);
//    }
//    //VideoClient
//    private static boolean deviceIsUartVideoDevice(UsbDevice device) {
//        return device != null && (device.getVendorId() == 4292 && device.getProductId() == 60000);
//    }
//    //VideoClient
//    private synchronized void disconnected() {
//        //video.stopPlayback();
//        uartDevice = null;
//    }
//    //VideoClient
//    final USBMonitor.OnDeviceConnectListener mOnDeviceConnectListener = new USBMonitor.OnDeviceConnectListener() {
//        // USB device attach
//        @Override
//        public void onAttach(final UsbDevice device) {
//            System.out.println("onAttach");

//            if(deviceHasConnected(device) || uartDevice != null)
//                return;

//              new Thread(new Runnable() {
//                  @Override
//                  public void run() {
//                      try {
//                          if (device == null) {
//                              final List<UsbDevice> devices = mUSBMonitor.getDeviceList();
//                              if (devices.size() == 1) {
//                                  mUSBMonitor.requestPermission(devices.get(0));
//                              }
//                          } else {
//                              mUSBMonitor.requestPermission(device);
//                          }
//                      } catch (Exception e){
//                          e.printStackTrace();
//                      }
//                  }
//              }).start();
//        }

//        // USB device detach
//        @Override
//        public void onDettach(UsbDevice device) {
//            System.out.println("onDetach");
//            if(!deviceIsUartVideoDevice(device)) {
//                return;
//            }

//            if(!deviceHasConnected(device)) {
//                return;
//            }

//            disconnected();
//        }

//        // USB device has obtained permission
//        @Override
//        public void onConnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock, boolean createNew) {
//            System.out.println("onConnect");
//            if(!deviceIsUartVideoDevice(device)) {
//                return;
//            }
//            if(deviceHasConnected(device)) {
//                return;
//            }

//            synchronized (this) {
//                if(deviceIsUartVideoDevice(device)) {
//                    if(video.openUsbSerial(device))
//                    {
//                        uartDevice = device;
//                    }
//                }
//            }
//        }

//        @Override
//        public void onDisconnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock) {
//            System.out.println("onDisconnect");
//            if(!deviceIsUartVideoDevice(device)) {
//                return;
//            }

//            if(!deviceHasConnected(device)) {
//                return;
//            }

//            disconnected();
//        }

//        // USB device obtained permission failed
//        @Override
//        public void onCancel() {
//            System.out.println("onCancel");
//        }
//    };

//    public void start(){

//       threadPool.execute(new VideoConsumerTask(filaPacotesVideo));



//        video = new VideoClient(context);
//        Log.d("MyApp","new videoclient!");

//        video.setClientListener(new VideoClient.ClientListener() {

//                @Override
//                public void onH264Received(byte[] h264) {
//                    Log.d("MyApp","new video producer task!");
//                    threadPool.execute(new VideoProducerTask(filaPacotesVideo, h264));
//                }

//                @Override
//                public void onGPSReceived(byte[] bytes) {
//                    // GPS data callback
//                }

//                @Override
//                public void onDataReceived(byte[] bytes) {
//                    // Transmission data callback
//                }

//                @Override
//                public void onDebugReceived(byte[] bytes) {
//                    // Debug data callback
//                }

//                @Override
//                public void onError(String error) {
//                    // Error data callback
//                }
//        });

//        Log.d("MyApp","new usbmonitor!");

//        mUSBMonitor = new USBMonitor(context, mOnDeviceConnectListener);
//        final List<DeviceFilter> filters = DeviceFilter.getDeviceFilters(context, R.xml.device_filter);
//        mUSBMonitor.setDeviceFilter(filters);
//        mUSBMonitor.register();


//        Log.d("MyApp","new usbmonitor!");
//    }

//    public void setVideoListener(){

//    }

//    public void stop(){
//        disconnected();
//        threadPool.shutdown();

//        if (mUSBMonitor != null) {
//            mUSBMonitor.unregister();
//            mUSBMonitor.destroy();
//            mUSBMonitor = null;
//        }
//    }

//    public void destroy(){
//        disconnected();

//        if (mUSBMonitor != null) {
//            mUSBMonitor.unregister();
//            mUSBMonitor.destroy();
//            mUSBMonitor = null;
//        }
//    }
//}
