package com.example.caringhands

import android.R
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.caringhands.DashboardActivity
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.caringhands.R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        signUpTv.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, SignupActivity::class.java)
            startActivity(intent)
            finish()
        })
        login.setOnClickListener(View.OnClickListener {
                var email = Etemail.text.toString()
            println(email)
                var password = Etpassword.text.toString()
            println(password)
                if (Etemail==null) {
                    Etemail.error = "Enter your email"

                }
            if (Etpassword==null) {
                    Etpassword.error = "Enter your password"
                }
if(email!= null && password != null)
{
    mAuth!!.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(
            this
        )
        { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@MainActivity, "Login Successfully", Toast.LENGTH_LONG)
                    .show()
                val intent =
                    Intent(this@MainActivity, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@MainActivity, "Sign In fail!", Toast.LENGTH_LONG).show()
            }
        }
}

        })



    }
}
