package co.com.petsvet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import co.com.petsvet.modelo.Servicios
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_servicios.*
import java.util.*

class ServiciosActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    private val TAG = "DocSnippets"
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)


        var botonGuardar = buttonGuardarServicio;
        var botonListar = buttonListarServicios;
        var botonQr = buttonQR;

        botonGuardar.setOnClickListener {
            var servicioId = UUID.randomUUID().toString()
            var tituloServicio = txtTituloServicio.text.toString()
            var dueño = txtDueñoServicio.text.toString()
            var descripcion = txtDescripcionServicio.text.toString()
            var calificacion = "5.0"
            var validation = false

            if (tituloServicio == ""){
                txtTituloServicio.error = "Campo Requerido";
            }
            if (dueño == ""){
                txtDueñoServicio.error = "Campo Requerido";
                validation = false
            }
            if (descripcion == ""){
                txtDescripcionServicio.error = "Campo Requerido";
                validation = false
            }
            if (validation) {
                val servicio = Servicios(servicioId,tituloServicio, dueño, descripcion, calificacion)
                db.collection("Servicios").add(servicio).addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
                }
                Toast.makeText(this, "Servicio Insertado", Toast.LENGTH_LONG).show();
            }
        }

        botonListar.setOnClickListener {
            val intent = Intent(this, ListaServiciosActivity::class.java)
            startActivity(intent)
        }

        botonQr.setOnClickListener {
            IntentIntegrator(this).initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if (result != null){
            if (result.contents != null){
                val builder =
                    AlertDialog.Builder(this)
                builder.setTitle("Resultado QR")
                builder.setMessage("profe pasamos el QR contiene:\\n\\n" + result.contents)
                builder.setPositiveButton("Aceptar", null)

                val dialog = builder.create()
                dialog.show()
                //textQr.setText("El codigo trajo" + result.contents);
            }else{

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
