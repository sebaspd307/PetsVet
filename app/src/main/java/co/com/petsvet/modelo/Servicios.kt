package co.com.petsvet.modelo

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Servicios (
    var servicioId: String? = "",
    var titulo: String? = "DEFAULT TITULO",
    var dueño: String? = "DEFAULT DUEÑO",
    var descripcion: String? = "DEFAULT DESCRIPCION",
    //var urlImagen: Int? = 0,
    var calificacion: String? = "DEFAULT CALIFICACION"
    )