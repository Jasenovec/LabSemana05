package com.jasenovec.labsemana05

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.jasenovec.labsemana05.databinding.FragmentCalculadoraBinding
import kotlin.math.pow

class CalculadoraFragment : Fragment() {

    private var _binding: FragmentCalculadoraBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculadoraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val operaciones = arrayOf("Suma", "Resta", "Multiplicación", "División", "Potenciación")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            operaciones
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOperaciones.adapter = adapter

        binding.btnCalcular.setOnClickListener {
            val num1 = binding.editTextNumber1.text.toString().toDoubleOrNull()
            val num2 = binding.editTextNumber2.text.toString().toDoubleOrNull()

            if (num1 != null && num2 != null) {
                val resultado = when (binding.spinnerOperaciones.selectedItem.toString()) {
                    "Suma" -> num1 + num2
                    "Resta" -> num1 - num2
                    "Multiplicación" -> num1 * num2
                    "División" -> if (num2 != 0.0) num1 / num2 else "No se puede dividir por cero"
                    "Potenciación" -> num1.pow(num2)
                    else -> "Operación no válida"
                }
                binding.tvResultado.text = "Resultado: $resultado"
            } else {
                binding.tvResultado.text = "Por favor ingrese números válidos"
            }
        }

        // Acción del botón Regresar
        binding.btnRegresar.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
