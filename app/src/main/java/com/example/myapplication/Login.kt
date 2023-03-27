package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var vistaBinding: ActivityLoginBinding
    private lateinit var autenticacionFirebase: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vistaBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(vistaBinding.root)


        autenticacionFirebase = FirebaseAuth.getInstance()


        vistaBinding.loginButton.setOnClickListener {
            val correoElectronico = vistaBinding.usuarioText.text.toString()
            val contrasena = vistaBinding.contraseAText.text.toString()

            if (correoElectronico.isNotEmpty() && contrasena.isNotEmpty()) {

                autenticacionFirebase.signInWithEmailAndPassword(correoElectronico, contrasena).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "No se permiten campos vacíos", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onStart() {
        super.onStart()

        if(autenticacionFirebase.currentUser != null){
            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
        }
    }
}
