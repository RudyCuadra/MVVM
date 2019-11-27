package ni.abril.azb.megaboicotapp.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ni.devotion.mvvm.model.BusinessList
import ni.devotion.mvvm.view_holder.BusinessViewHolder

class BusinessAdapter : ListAdapter<BusinessList, BusinessViewHolder>
    (DIFF_CALLBACK){
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BusinessList>() {
            override fun areItemsTheSame(oldItem: BusinessList,
                                         newItem: BusinessList) =
                oldItem.id== newItem.id
            override fun areContentsTheSame(oldItem: BusinessList,
                                            newItem: BusinessList) =
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