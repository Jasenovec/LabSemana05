package com.jasenovec.labsemana05

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jasenovec.labsemana05.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        binding.tvWelcome.text = "Bienvenido $username"

        binding.btLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Cargar fragmentos al hacer clic en los botones
        binding.btnCurrencyExchange.setOnClickListener {
            replaceFragment(CambioDivisasFragment())  // Reemplaza el fragment actual con el de Cambio de Divisas
        }

        binding.btnCalculator.setOnClickListener {
            replaceFragment(CalculadoraFragment())  // Reemplaza el fragment actual con el de Calculadora
        }
    }

    // Función para reemplazar fragmentos
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)  // Aquí usas el ID del FrameLayout en el XML
        fragmentTransaction.addToBackStack(null)  // Permite regresar al fragment anterior al presionar el botón de "Atrás"
        fragmentTransaction.commit()
    }
}
