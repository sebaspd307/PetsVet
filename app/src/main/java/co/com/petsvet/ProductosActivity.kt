package co.com.petsvet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.com.petsvet.modelo.Registro

class ProductosActivity : AppCompatActivity() {

    private lateinit var lista: ArrayList<Registro>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)
    }
}
