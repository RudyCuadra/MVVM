package ni.devotion.mvvm.data.network.`interface`

import ni.devotion.mvvm.model.Categories
import retrofit2.http.GET

interface CategoriesInterface {

    @GET("/api/v1/categoria")
    suspend fun requestCategoriesList(): List<Categories>
}