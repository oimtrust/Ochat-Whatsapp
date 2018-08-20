package com.oimtrust.ochatwhatsapp

import android.content.ComponentName
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    internal var TAG = "No Save Whatsapp Number"
    lateinit var nextBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        nextBtn = findViewById<View>(R.id.btnNext) as ImageView

        nextBtn.setOnClickListener { view -> sendchat(view) }
    }

    fun sendchat(view: View) {
        val phonenumber = findViewById<EditText>(R.id.editText_phoneNumber)
        val number = phonenumber.text.toString()

        if (!number.isEmpty()) {
            openWhatsApp(number)
        }
    }

    private fun openWhatsApp(number: String) {
        var number = number
        try {
            if (number.startsWith("0")) {
                number = number.substring(1)
                number = "62$number"
            }
            number = number.replace(" ", "").replace("+", "")

            val sendIntent = Intent("android.intent.action.MAIN")
            sendIntent.component = ComponentName("com.whatsapp", "com.whatsapp.Conversation")
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(number) + "@s.whatsapp.net")
            startActivity(sendIntent)

        } catch (e: Exception) {
            Log.e(TAG, "ERROR_OPEN_MESSANGER" + e.toString())
        }

    }
}
