package com.example.caringhands

import android.content.Intent

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.Etemail
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : AppCompatActivity() {



    private lateinit var progressBar:ProgressBar

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        signInTv.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SignupActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        })
        register.setOnClickListener(View.OnClickListener {
            var email: String = Etemail.text.toString()
            var password1: String = Etpassword1.text.toString()
            var password2: String = Etpassword2.text.toString()
            val mail= Etemail.text.toString();
            auth= FirebaseAuth.getInstance()
            if (TextUtils.isEmpty(email)) {
                //     email?.setText("some text")
                // email?.setError("Enter email");
                Etemail?.setError("Enter your email")
            }
            if (TextUtils.isEmpty(password1)) {
                Etpassword1?.setError("Enter your password")
            }
            if (TextUtils.isEmpty(password2)) {
                Etpassword2?.setError("Confirm your password")
            }
            if (Etpassword1 != Etpassword2) {
                Etpassword2?.setError("Different password")
            }
            if (password1.length < 4) {
                Etpassword1?.setError("Length should be > 4")
            }
                auth.createUserWithEmailAndPassword(email, password1)
                    ?.addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Created", Toast.LENGTH_LONG).show()
                            val user: FirebaseUser? = auth.currentUser
                            verifyEmail(user)
                            action()
                        }
                    }
            })
        }
    private fun verifyEmail(user:FirebaseUser?){
        user?.sendEmailVerification()?.addOnCompleteListener(this){
                task ->
            if(task.isComplete){
                Toast.makeText(this, "User Created",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Error in user creation",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun action(){
        startActivity(Intent(this,DashboardActivity::class.java))
    }

}





