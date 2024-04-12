package com.test.whtsapautoreply;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.URLEncoder;

public class MainActivity2 extends AppCompatActivity {


    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        this.send = (Button) findViewById(R.id.send);
        this.send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity2 mainActivity = MainActivity2.this;
                if (!mainActivity.isAccessibilityOn(mainActivity.getApplicationContext(), WhatsappAccessibilityService.class)) {
                    MainActivity2.this.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
                    Toast.makeText(MainActivity2.this, "iffff", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity2.this, "elseeee", Toast.LENGTH_SHORT).show();
                try {
                    Intent sendMsg = new Intent("android.intent.action.VIEW");
                    sendMsg.setPackage("com.whatsapp");
                    sendMsg.setData(Uri.parse("https://api.whatsapp.com/send?phone=+91 7698408073&text=" + URLEncoder.encode("Your Message to Contact Number", "UTF-8")));
                    if (sendMsg.resolveActivity(MainActivity2.this.getPackageManager()) != null) {
                        MainActivity2.this.startActivity(sendMsg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /* access modifiers changed from: private */
    public boolean isAccessibilityOn(Context applicationContext, Class<WhatsappAccessibilityService> whatsappAccessibilityServiceClass) {
        String settingValue;
        int accessibilityEnabled = 0;
        String service = getPackageName() + "/" + whatsappAccessibilityServiceClass.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(applicationContext.getApplicationContext().getContentResolver(), "accessibility_enabled");
        } catch (Settings.SettingNotFoundException e) {
        }
        TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(':');
        if (accessibilityEnabled != 1 || (settingValue = Settings.Secure.getString(applicationContext.getApplicationContext().getContentResolver(), "enabled_accessibility_services")) == null) {
            return false;
        }
        colonSplitter.setString(settingValue);
        while (colonSplitter.hasNext()) {
            if (colonSplitter.next().equalsIgnoreCase(service)) {
                return true;
            }
        }
        return false;
    }
}