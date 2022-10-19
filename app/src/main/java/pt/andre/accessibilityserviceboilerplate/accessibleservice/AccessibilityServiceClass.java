package pt.andre.accessibilityserviceboilerplate.accessibleservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import pt.andre.accessibilityserviceboilerplate.overlay.Overlay;

public class AccessibilityServiceClass  extends AccessibilityService {

    final static String TAG = AccessibilityServiceClass.class.getCanonicalName();
    public static String s;
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
            if(!packageName.equals(bp) && !packageName.equals(ui)){
                s = packageName.toString();
                System.out.println(s);
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}