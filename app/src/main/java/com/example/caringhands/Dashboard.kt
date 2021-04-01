package com.example.caringhands

import android.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.caringhands.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.caringhands.R.layout.activity_dashboard)
        logout.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@DashboardActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}