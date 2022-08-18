package com.example.sendmessageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Message;
import android.service.carrier.CarrierMessagingService;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {
    //initialize variable

    EditText editTextPhone, editTextMessage;
    Button btnSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign variable
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextMessage = findViewById(R.id.editTextMessage);
        btnSent = findViewById(R.id.btnSent);

        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check condition for permission
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED) {
                    //when permission is granted
                    //crate a method
                    SendSMS();
                } else {
                    //when permission is not granted
                    //request for permission
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}
                            , 100);
                }
            }
        });
    }

    private void SendSMS() {
        //get value form editText
        String Phone = editTextPhone.getText().toString();
        String Message = editTextMessage.getText().toString();

        //check condition if string is empty or not
        if (!Phone.isEmpty() && !Message.isEmpty()) {
            //initialize SMS manager
            SmsManager smsManager = SmsManager.getDefault();
            //send message
            smsManager.sendTextMessage(Phone, null, Message, null, null);
            //display Toast msg
            Toast.makeText(this, "SMS sent Successfully", Toast.LENGTH_SHORT).show();
        } else {
            //when string is empty then display toast msg
            Toast.makeText(this, "Please enter phone and message", Toast.LENGTH_SHORT).show();

        }

    }
}