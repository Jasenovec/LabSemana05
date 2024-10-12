package com.jasenovec.labsemana05

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jasenovec.labsemana05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //2. Inicializamos el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)  //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //3. Configuramos el botón para iniciar sesión
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_LONG).show()
            } else if (password != "1234") {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show()
            } else {                //4. Logica de autenticacion
                //Para este ejemplo asumiremos cualquier user y pass
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
            }
        }

        // Configuramos el botón para mostrar/ocultar la contraseña

        binding.btnTogglePasswordVisibility.setOnClickListener {
            if (isPasswordVisible) {
                binding.etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.btnTogglePasswordVisibility.text = "Mostrar"
            } else {
                binding.etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.btnTogglePasswordVisibility.text = "Ocultar"
            }
            isPasswordVisible = !isPasswordVisible
            binding.etPassword.setSelection(binding.etPassword.text.length) // Mantiene el cursor al final
        }
    }
}