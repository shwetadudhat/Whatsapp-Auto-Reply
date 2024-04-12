package com.test.whtsapautoreply;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
            // Incoming call
            sendMessages(context, phoneNumber);
        } else if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
            // Call Answered
            sendMessages(context, phoneNumber);
        } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
            // Call Ended (Handle Missed, Outgoing, and Dialed calls)
            if (wasMissedCall(context, phoneNumber)) {
                // Missed call
                sendMessages(context, phoneNumber);
            } else if (wasOutgoingCall(context, phoneNumber)) {
                // Outgoing call
                sendMessages(context, phoneNumber);
            } else if (wasDialedCall(context, phoneNumber)) {
                // Dialed call
                sendMessages(context, phoneNumber);
            } else if (wasRejectedCall(context, phoneNumber)) {
                // Rejected call
                sendMessages(context, phoneNumber);
            }
        }

    }

    private boolean wasMissedCall(Context context, String phoneNumber) {
        boolean missedCall = false;
        String selection = CallLog.Calls.TYPE + " = ? AND " + CallLog.Calls.NUMBER + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(CallLog.Calls.MISSED_TYPE), phoneNumber};
        String sortOrder = CallLog.Calls.DATE + " DESC";

        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                    null,
                    selection,
                    selectionArgs,
                    sortOrder);

            if (cursor != null && cursor.moveToFirst()) {
                @SuppressLint("Range") int callType = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
                missedCall = callType == CallLog.Calls.MISSED_TYPE;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return missedCall;
    }


    private boolean wasOutgoingCall(Context context, String phoneNumber) {
        boolean outgoingCall = false;
        String selection = CallLog.Calls.TYPE + " = ? AND " + CallLog.Calls.NUMBER + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(CallLog.Calls.OUTGOING_TYPE), phoneNumber};
        String sortOrder = CallLog.Calls.DATE + " DESC";

        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                    null,
                    selection,
                    selectionArgs,
                    sortOrder);

            if (cursor != null && cursor.moveToFirst()) {
                outgoingCall = true;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return outgoingCall;
    }


    private boolean wasDialedCall(Context context, String phoneNumber) {
        boolean dialedCall = false;
        String selection = CallLog.Calls.TYPE + " = ? AND " + CallLog.Calls.NUMBER + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(CallLog.Calls.OUTGOING_TYPE), phoneNumber};
        String sortOrder = CallLog.Calls.DATE + " DESC";

        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                    null,
                    selection,
                    selectionArgs,
                    sortOrder);

            if (cursor != null && cursor.moveToFirst()) {
                dialedCall = true;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return dialedCall;
    }

    private void sendMessages(Context context, String phoneNumber) {
        // Send SMS
        sendSMS(context,phoneNumber, "Your SMS Message");

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

    private boolean wasRejectedCall(Context context, String phoneNumber) {
        boolean rejectedCall = false;
        String selection = CallLog.Calls.TYPE + " = ? AND " + CallLog.Calls.NUMBER + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(CallLog.Calls.REJECTED_TYPE), phoneNumber};
        String sortOrder = CallLog.Calls.DATE + " DESC";

        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                    null,
                    selection,
                    selectionArgs,
                    sortOrder);

            if (cursor != null && cursor.moveToFirst()) {
                rejectedCall = true;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return rejectedCall;
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
