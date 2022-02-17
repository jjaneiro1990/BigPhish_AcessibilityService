package pt.andre.accessibilityserviceboilerplate.overlay;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.WINDOW_SERVICE;

import android.accessibilityservice.AccessibilityService;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import pt.andre.accessibilityserviceboilerplate.R;

public class Overlay {
    WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT);

    private WindowManager windowManager;
    private LayoutInflater li;
    private ViewGroup rootViewGroup;
    private AccessibilityService service;
    private TextView tv;

    private static Overlay instance;

    public static Overlay getInstance(){

        if (instance == null)
            instance = new Overlay();

        return instance;
    }

    public AccessibilityService getService(){
        return service;
    }

    public void setService(AccessibilityService service) {
        this.li = (LayoutInflater) service.getSystemService(LAYOUT_INFLATER_SERVICE);
        this.windowManager = (WindowManager) service.getSystemService(WINDOW_SERVICE);
        this.service = service;
    }

    public void showOverlay(){
        rootViewGroup = (ViewGroup) li.inflate(R.layout.overlay, null);
        rootViewGroup.findViewById(R.id.bt_fg).setOnClickListener(v -> {});
        tv = rootViewGroup.findViewById((R.id.tv_fg));
        params.gravity = Gravity.TOP;
        addView(rootViewGroup, params);
    }

    public void hideOverlay(){
        removeView(rootViewGroup);
    }

    private void addView(View v, WindowManager.LayoutParams lp){
        windowManager.addView(v, lp);
    }

    private void removeView(View v){
        try{
            windowManager.removeView(v);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}