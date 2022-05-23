package com.activity.group7_sms_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Send_SMSN extends AppCompatActivity {
    final private int REQUEST_SEND_SMS = 123;

    private EditText txtPhoneNumber, txtMessage;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        txtMessage = findViewById(R.id.txtMessage);

        buttonSend = findViewById(R.id.button_Send);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    REQUEST_SEND_SMS);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SEND_SMS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Send_SMSN.this,
                            "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Send_SMSN.this,
                            "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode,
                        permissions, grantResults);
        }
    }


    public void onClick(View v) {
//---the "phone number" of your emulator should be 5554---


        sendSMS();
    }
    //---sends an SMS message---
    private void sendSMS()
    {
        String PhoneNo = txtPhoneNumber.getText().toString().trim();
        String Sms = txtMessage.getText().toString().trim();

        if(!PhoneNo.equals("") && !Sms.equals("")){
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(PhoneNo, null, Sms, null, null);
            Toast.makeText(Send_SMSN.this,
                    "Message Sent successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(Send_SMSN.this,
                    "Enter value first", Toast.LENGTH_SHORT).show();
        }


    }

}