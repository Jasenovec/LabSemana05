package com.jasenovec.labsemana05

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.jasenovec.labsemana05.databinding.FragmentCambioDivisasBinding

class CambioDivisasFragment : Fragment() {

    private var _binding: FragmentCambioDivisasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCambioDivisasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),  // Usamos requireContext() en lugar de "this"
            R.array.tipo_divisa,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDivisas.adapter = adapter

        // Listener del Spinner
        binding.spinnerDivisas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedOption = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Acción del botón convertir
        binding.convertir.setOnClickListener {
            val monto = binding.editTextMonto.text.toString().toDoubleOrNull()
            val resultado = when (binding.spinnerDivisas.selectedItem.toString()) {
                "Soles a Dólares" -> monto?.let { it / 3.5 }
                "Dólares a Soles" -> monto?.let { it * 3.5 }
                else -> 0.0
            }
            binding.resultado.text = resultado.toString()
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
