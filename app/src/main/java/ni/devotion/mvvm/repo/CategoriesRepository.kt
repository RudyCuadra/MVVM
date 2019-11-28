package ni.devotion.mvvm.repo

import ni.devotion.mvvm.data.network.`interface`.BusinessInterface
import ni.devotion.mvvm.data.network.`interface`.CategoriesInterface
import ni.devotion.mvvm.model.BusinessList
import ni.devotion.mvvm.model.Categories

interface CategoriesRepository {
    suspend fun getCategories(): List<Categories>
}

class  CategoriesRepositoryImpl(private val categoriesService: CategoriesInterface):
    CategoriesRepository{

    override suspend fun getCategories(): List<Categories> {
        return categoriesService.requestCategoriesList()
    }

}