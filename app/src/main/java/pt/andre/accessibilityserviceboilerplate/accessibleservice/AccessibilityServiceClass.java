package pt.andre.accessibilityserviceboilerplate.accessibleservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import java.util.List;

import pt.andre.accessibilityserviceboilerplate.overlay.Overlay;

public class AccessibilityServiceClass  extends AccessibilityService {

    final static String TAG = AccessibilityServiceClass.class.getCanonicalName();
    public static String s;
    public static String pack;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Overlay.getInstance().setService(this);
        Overlay.getInstance().showOverlay();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent: " + event.toString());
        CharSequence packageName = event.getPackageName();
        if(packageName != null){
            String bp = "pt.andre.accessibilityserviceboilerplate";
            String ui = "com.android.systemui";
            if(packageName.equals(ui)){
                pack = "Sistema Operativo";
            }
            if(!packageName.equals(bp) && !packageName.equals(ui)){
                s = packageName.toString();
                pack = transformPackage(s);
                System.out.println(s);
            }
        }
    }


    private String transformPackage(String s) {
        String s1 = "";
        switch(s){
            case "com.whatsapp":
                s1 = "Whatsapp";
                break;
            case "com.google.android.gm":
                s1 = "Gmail";
                break;
            case "com.instagram.android":
                s1 = "Instagram";
                break;
            case "com.facebook.katana":
                s1 = "Facebook";
                break;
            default:
                s1 = "Not recognised";
                break;
        }
        return s1;
    }


    @Override
    public void onInterrupt() {

    }
}