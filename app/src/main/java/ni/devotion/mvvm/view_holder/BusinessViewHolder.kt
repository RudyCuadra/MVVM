package ni.devotion.mvvm.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.business_content.*
import ni.devotion.mvvm.BuildConfig
import ni.devotion.mvvm.R
import ni.devotion.mvvm.data.network.`interface`.IRecyclerClic
import ni.devotion.mvvm.model.BusinessList

class BusinessViewHolder constructor(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer, View.OnClickListener{
    var listener: IRecyclerClic?=null
    lateinit var business: BusinessList

    fun bind(business: BusinessList, iRecyclerClic: IRecyclerClic) {
        val imgPrueba = "https://9to5google.com/wp-content/uploads/sites/4/2019/03/android_figure_1.jpg?quality=82&strip=all&w=1600"
        var id_negocio = business.id
        println("Nombre: ${business.nombre}")
        departmentName.text = business.nombre
        departmentDescripcion.text = business.descripcion
        bussiness_image.load(BuildConfig.API_URL+business.imagenes.get(0).toString()){
            println("URL IMAGEN 1: ${BuildConfig.API_URL+business.imagenes.get(0)}")
            placeholder(R.mipmap.ic_launcher) }
        verMas.text = "Ver más"

        containerView.setOnClickListener {
            iRecyclerClic.onClick(containerView,adapterPosition, business)
        }

        //listener?.invoke()
        this.listener = iRecyclerClic
    }

    companion object {
        fun create(parent: ViewGroup): BusinessViewHolder {
            return BusinessViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.busines_card, parent, false))
        }
    }

    override fun onClick(v: View?) {
        this.listener?.onClick(v!!, adapterPosition, business)
    }

}
