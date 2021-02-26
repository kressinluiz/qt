package org.qtproject.example.jnimessenger;

import android.util.Log;

public class JniMessenger
{
    private static native void callFromJava(String message);

    public JniMessenger() {}

    public static void printFromJava(String message)
    {
        Log.d("JniMessenger", "printFromJava BEGIN");
        System.out.println("This is printed from JAVA, message is: " + message);
        callFromJava("Hello from JAVA!");
        Log.d("JniMessenger", "printFromJava END");
    }
}
