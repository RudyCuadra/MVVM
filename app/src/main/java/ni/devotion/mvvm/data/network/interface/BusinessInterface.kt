package ni.devotion.mvvm.data.network.`interface`


import ni.devotion.mvvm.model.BusinessList
import retrofit2.http.GET

interface BusinessInterface {

    @GET("api/v1/negocio")
    suspend fun requestBusinessList(): List<BusinessList>
}