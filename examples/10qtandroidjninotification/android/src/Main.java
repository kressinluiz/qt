package org.kressin.example;

import org.qtproject.qt5.android.QtNative;
import android.app.Activity;

public class Main extends Activity
{
    protected Activity activity;

    public Main()
    {
        activity = QtNative.activity();
    }
}
