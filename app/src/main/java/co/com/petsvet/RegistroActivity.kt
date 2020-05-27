package co.com.petsvet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import co.com.petsvet.modelo.Registro
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_registro.*
import java.util.*


class RegistroActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private val TAG = "DocSnippets"
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val button = buttonRegistro;
        button.setOnClickListener {

            val usuarioId = UUID.randomUUID().toString()
            val nomUsuario = txtUsuarioRegistro.text.toString()
            val contraseña = txtContrasenaRegistro.text.toString()
            val correo = txtCorreoRegistro.text.toString()

            if (nomUsuario == ""){
                txtUsuarioRegistro.error = "Campo Requerido";
            }
            if (contraseña == ""){
                txtContrasenaRegistro.error = "Campo Requerido";
            }
            if (correo == ""){
                txtCorreoRegistro.error = "Campo Requerido";
            }
            val registro = Registro(usuarioId,nomUsuario,correo,contraseña)
            db.collection("Usuarios").add(registro).addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Usuario Insertado",Toast.LENGTH_LONG).show();
        }

    }
}
