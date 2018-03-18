package com.oimtrust.ochatwhatsapp;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    String TAG = "No Save Whatsapp Number";
    ImageView nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        nextBtn = (ImageView) findViewById(R.id.btnNext);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendchat(view);
            }
        });
    }

    public void sendchat(View view){
        EditText phonenumber =  findViewById(R.id.editText_phoneNumber);
        String number = phonenumber.getText().toString();

        if (!number.isEmpty()){
            openWhatsApp(number);
        }
    }

    private void openWhatsApp(String number) {
        try {
            if (number.startsWith("0")){
                number = number.substring(1);
                number = "62"+number;
            }
            number = number.replace(" ", "").replace("+", "");

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(number)+"@s.whatsapp.net");
            startActivity(sendIntent);

        } catch(Exception e) {
            Log.e(TAG, "ERROR_OPEN_MESSANGER"+e.toString());
        }
    }
}
