package com.lugares_u.ui.lugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lugares_u.R
import com.lugares_u.databinding.FragmentAddLugarBinding
import com.lugares_u.model.Lugar
import com.lugares_u.viewmodel.LugarViewModel


class AddLugarFragment : Fragment() {
    private var _binding: FragmentAddLugarBinding? = null
    private val binding get() = _binding!!
    private lateinit var lugarViewModel: LugarViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lugarViewModel =
            ViewModelProvider(this)[LugarViewModel::class.java]
        _binding = FragmentAddLugarBinding.inflate(inflater,container,false)
        binding.btAgregar.setOnClickListener{
            addLugar()
        }
        return binding.root
    }

    private fun addLugar() {
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreo.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        if(nombre.isNotEmpty()) {// si puedo agregar un lugar
            val lugar = Lugar(0,nombre,correo,telefono,web,0.0,0.0,
            0.0,"","")
            lugarViewModel.addLugar(lugar)
            Toast.makeText(requireContext(),"Lugar agregado",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addLugarFragment_to_nav_lugar)
        } else { //mensaje de error
            Toast.makeText(requireContext(),"Faltan datos!!",Toast.LENGTH_LONG).show()
        }


    }

}