package ni.devotion.mvvm.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.business_content.*
import ni.devotion.mvvm.R
import ni.devotion.mvvm.model.BusinessList

class BusinessViewHolder constructor(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(business: BusinessList) {
        println("Nombre: ${business.nombre}")
        //departmentName.text = business.success.toString()
        departmentName.text = business.nombre
        departmentDescripcion.text = business.descripcion
        verMas.text = "Ver más"
    }

    companion object {
        fun create(parent: ViewGroup): BusinessViewHolder {
            return BusinessViewHolder(LayoutInflater.from(parent.context).
                inflate(R.layout.busines_card, parent, false))
        }
    }
}