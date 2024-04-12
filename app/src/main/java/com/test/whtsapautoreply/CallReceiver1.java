package com.test.whtsapautoreply;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.CallLog;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CallReceiver1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);


        // Handle phone call events here
        // Trigger a service to send messages based on call events
        Intent serviceIntent = new Intent(context, MessageSendingService.class);
        serviceIntent.putExtra("phone_number", phoneNumber);
        context.startService(serviceIntent);
    }

    class MessageSendingService extends Service {
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            String phoneNumber = intent.getStringExtra("phone_number");

            // Logic to send messages based on phone call events
            // Call the sendMessages method here
            if (phoneNumber != null) {
//                 if (wasOutgoingCall(getApplicationContext(), phoneNumber)) {
                    // Outgoing call
                    sendMessages(getApplicationContext(), phoneNumber);
//                }
//                sendMessages(getApplicationContext(), phoneNumber);
            }

            return START_STICKY;
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        private void sendMessages(Context context, String phoneNumber) {
            // Send SMS
            sendSMS(context, phoneNumber, "Your SMS Message");

            // Send WhatsApp Message
            sendWhatsAppMessage(context, phoneNumber, "Your WhatsApp Message");
        }

        private void sendSMS(Context context,String phoneNumber, String message) {
            // Code to send SMS
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                Toast.makeText(context, "SMS sent to " + phoneNumber, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, "Failed to send SMS", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

        private void sendWhatsAppMessage(Context context, String phoneNumber, String message) {
            // Code to send WhatsApp message
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(Intent.ACTION_VIEW);

            try {
                String url = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + Uri.encode(message);
                intent.setPackage("com.whatsapp");
                intent.setData(Uri.parse(url));

                if (intent.resolveActivity(packageManager) != null) {
                    context.startActivity(intent);
                    Toast.makeText(context, "WhatsApp message sent to " + phoneNumber, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "WhatsApp is not installed", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(context, "Failed to send WhatsApp message", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

    }
}


