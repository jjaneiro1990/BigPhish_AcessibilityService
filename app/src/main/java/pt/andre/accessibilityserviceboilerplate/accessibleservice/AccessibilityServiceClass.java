package pt.andre.accessibilityserviceboilerplate.accessibleservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import pt.andre.accessibilityserviceboilerplate.overlay.Overlay;

public class AccessibilityServiceClass  extends AccessibilityService {

    final static String TAG = AccessibilityServiceClass.class.getCanonicalName();

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Overlay.getInstance().setService(this);
        Overlay.getInstance().showOverlay();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent: " + event.toString());
    }

    @Override
    public void onInterrupt() {

    }
}