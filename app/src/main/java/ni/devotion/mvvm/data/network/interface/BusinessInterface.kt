package ni.devotion.mvvm.data.network.`interface`


import android.view.View
import ni.devotion.mvvm.model.BusinessList
import ni.devotion.mvvm.model.Categories
import retrofit2.http.GET

interface BusinessInterface {

    @GET("api/v1/negocio")
    suspend fun requestBusinessList(): List<BusinessList>

    @GET("/negocio")
    suspend fun requestBusinessDesc(): List<BusinessList>

    suspend fun onClick(view: View, position: Int)
}