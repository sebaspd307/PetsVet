package co.com.petsvet.modelo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.com.petsvet.modelo.Servicios

class MainViewModel: ViewModel() {
    private val repo = RepoServicios()

    fun fetchServiciosData(): LiveData<MutableList<Servicios>>{

        val mutableData = MutableLiveData<MutableList<Servicios>>()
        repo.getServiciosData().observeForever{servicioList ->
            mutableData.value = servicioList
        }
        return mutableData
    }
}