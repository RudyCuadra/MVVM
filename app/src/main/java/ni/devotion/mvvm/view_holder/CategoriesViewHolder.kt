package ni.devotion.mvvm.view_holder

import android.graphics.drawable.DrawableContainer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.categoria_content.*
import ni.devotion.mvvm.R
import ni.devotion.mvvm.model.Categories

class CategoriesViewHolder constructor(override val containerView: View):
RecyclerView.ViewHolder(containerView), LayoutContainer{

    fun bind(categories: Categories){
        categoriaNombre.text = categories.nombre
    }

    companion object {
        fun create (parent: ViewGroup): CategoriesViewHolder{
            return CategoriesViewHolder(LayoutInflater.from(parent.context).
                inflate(R.layout.categoria_content, parent, false))
        }

    }
}