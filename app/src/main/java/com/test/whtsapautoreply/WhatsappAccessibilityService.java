package com.test.whtsapautoreply;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

import java.util.List;

public class WhatsappAccessibilityService extends AccessibilityService {

    private final AccessibilityServiceInfo info = new AccessibilityServiceInfo();

    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo source;
        List<AccessibilityNodeInfoCompat> sendMessageNodeInfoList;
        if (getRootInActiveWindow() != null && (source = event.getSource()) != null) {
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = null;
            if (Build.VERSION.SDK_INT >= 18) {
                findAccessibilityNodeInfosByViewId = source.findAccessibilityNodeInfosByViewId(" com.example.myapplication:id/send");
            }
            if (findAccessibilityNodeInfosByViewId.size() > 0) {
                Log.i("Required Text", findAccessibilityNodeInfosByViewId.get(0).getText().toString());
            }
            AccessibilityNodeInfoCompat rootInActiveWindow = AccessibilityNodeInfoCompat.wrap(getRootInActiveWindow());
            List<AccessibilityNodeInfoCompat> messageNodeList = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.whatsapp:id/entry");
            if (messageNodeList != null && !messageNodeList.isEmpty()) {
                AccessibilityNodeInfoCompat messageField = messageNodeList.get(0);
                if (messageField.getText() != null && messageField.getText().length() != 0 && messageField.getText().toString().endsWith(getApplicationContext().getString(R.string.whatsapp_suffix)) && (sendMessageNodeInfoList = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.whatsapp:id/send")) != null && !sendMessageNodeInfoList.isEmpty()) {
                    AccessibilityNodeInfoCompat sendMessageButton = sendMessageNodeInfoList.get(0);
                    if (sendMessageButton.isVisibleToUser()) {
                        sendMessageButton.performAction(16);
                        try {
                            Thread.sleep(500);
                            performGlobalAction(1);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        performGlobalAction(1);
                    }
                }
            }
        }
    }

    public void onInterrupt() {
    }

    public void onServiceConnected() {
        AccessibilityServiceInfo accessibilityServiceInfo = this.info;
        accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        accessibilityServiceInfo.notificationTimeout = 100;
        setServiceInfo(accessibilityServiceInfo);
    }
}
