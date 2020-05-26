package co.com.petsvet.modelo

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Registro(
    var usuarioId: String = "",
    var usuario: String? = "",
    var correo: String? = "",
    var contraseña: String? = ""

)