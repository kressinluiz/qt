package org.kressin.example;

import android.util.Log;

public class ExampleActivity extends org.qtproject.qt5.android.bindings.QtActivity
{
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
