package ni.devotion.mvvm.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ni.devotion.mvvm.model.Categories
import ni.devotion.mvvm.view_holder.CategoriesViewHolder

class CategoriesAdapter: ListAdapter<Categories, CategoriesViewHolder>
    (DIFF_CALLBACK){
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Categories>() {
            override fun areItemsTheSame(oldItem: Categories,
                                         newItem: Categories) =
                oldItem.id== newItem.id
            override fun areContentsTheSame(oldItem: Categories,
                                            newItem: Categories) =
                oldItem == newItem
        }
    }


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder =
        CategoriesViewHolder.create(parent)

}