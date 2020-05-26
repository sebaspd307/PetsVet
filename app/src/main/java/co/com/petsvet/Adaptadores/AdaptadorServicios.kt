package co.com.petsvet.Adaptadores


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.petsvet.R
import co.com.petsvet.modelo.Servicios
import kotlinx.android.synthetic.main.diseno_lista_servicio.view.*


class AdaptadorServicios(private val contex:Context): RecyclerView.Adapter<AdaptadorServicios.MainViewHolder>() {

    private var listaSericios = mutableListOf<Servicios>()

     fun setListData(data: MutableList<Servicios>){
        listaSericios = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(contex).inflate(R.layout.diseno_lista_servicio, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(listaSericios.size > 0){
            listaSericios.size
        }else{
            0;
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val servicios = listaSericios[position]
        holder.bindView(servicios)
    }

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindView(servicios:Servicios){
            itemView.txtTitulo.text = servicios.titulo;
            itemView.txtTexto.text = servicios.descripcion;
        }
    }
}