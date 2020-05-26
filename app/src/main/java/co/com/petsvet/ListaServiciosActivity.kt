package co.com.petsvet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.petsvet.Adaptadores.AdaptadorServicios
import co.com.petsvet.modelo.Servicios
import co.com.petsvet.modelo.viewModel.MainViewModel
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_lista_servicios.*

class ListaServiciosActivity : AppCompatActivity() {

    private lateinit var adapter: AdaptadorServicios
    val viewmodel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_servicios)

        adapter = AdaptadorServicios(this)

        RecyclerViewListaServicios.layoutManager = LinearLayoutManager(this)
        RecyclerViewListaServicios.adapter = adapter
        selecData()

    }

    fun selecData() {
        viewmodel.fetchServiciosData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}




