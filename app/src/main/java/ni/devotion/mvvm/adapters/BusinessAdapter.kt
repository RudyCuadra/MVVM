package ni.abril.azb.megaboicotapp.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ni.devotion.mvvm.model.Business
import ni.devotion.mvvm.view_holder.BusinessViewHolder

class BusinessAdapter : ListAdapter<Business, BusinessViewHolder>
    (DIFF_CALLBACK){
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Business>() {
            override fun areItemsTheSame(oldItem: Business,
                                         newItem: Business) =
                oldItem.data.id== newItem.data.id
            override fun areContentsTheSame(oldItem: Business,
                                            newItem: Business) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BusinessViewHolder.create(parent)

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        println("No se llena-----------------------------------------------------")
        holder.bind(getItem(position))
        println("AQUIIIIII: BIND: ${getItem(position)}")
    }
}