package com.ihfazh.yonotifme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseMessaging.getInstance().token.addOnCompleteListener{
            if (!it.isSuccessful){
                return@addOnCompleteListener
            }

            Toast.makeText(this, "Your token is ${it.result}", Toast.LENGTH_SHORT).show()
        }
    }
}