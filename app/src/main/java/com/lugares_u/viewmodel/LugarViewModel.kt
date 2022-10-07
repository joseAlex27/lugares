package com.lugares_u.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.lugares_u.data.LugarDatabase
import com.lugares_u.model.Lugar
import com.lugares_u.repository.LugarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LugarViewModel(application: Application) : AndroidViewModel(application) {
    val getLugares: LiveData<List<Lugar>>

    private val repository: LugarRepository

    init {
        val lugarDao = LugarDatabase.getDatabase(application).lugarDao()
        repository = LugarRepository(lugarDao)
        getLugares = repository.getLugares
    }

    fun addLugar (lugar: Lugar) {
        viewModelScope.launch(Dispatchers.IO) { repository.addLugar(lugar) }
    }

    fun updateLugar(lugar: Lugar) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateLugar(lugar) }
    }

    fun deleteLugar (lugar: Lugar) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteLugar(lugar) }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}