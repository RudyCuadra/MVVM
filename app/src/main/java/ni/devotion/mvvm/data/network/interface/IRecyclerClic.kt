package ni.devotion.mvvm.data.network.`interface`

import android.view.View
import ni.devotion.mvvm.model.BusinessList

interface IRecyclerClic {
    fun onClick(view: View, position:Int, business: BusinessList){
    }
}