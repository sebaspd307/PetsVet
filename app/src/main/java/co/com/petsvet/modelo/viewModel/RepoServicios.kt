package co.com.petsvet.modelo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.com.petsvet.modelo.Servicios
import com.google.firebase.firestore.FirebaseFirestore

class RepoServicios {

    fun getServiciosData():LiveData<MutableList<Servicios>>{
        val mutableData = MutableLiveData<MutableList<Servicios>>()
        FirebaseFirestore.getInstance().collection("Servicios").get().addOnSuccessListener {result ->
        val lisDatada = mutableListOf<Servicios>()
            for (document in result){

                val calificacion: String? = document.getString("calificacion")
                val servicioId: String? = document.getString("servicioId")
                val descripcion: String? = document.getString("descripcion")
                val dueño: String? = document.getString("dueño")
                val titulo: String? = document.getString("titulo")
                val servicio = Servicios(servicioId,titulo,dueño,descripcion,calificacion)
                lisDatada.add(servicio)
            }
            mutableData.value = lisDatada
        }
        return mutableData
    }
}