package ni.devotion.mvvm.repo

import ni.devotion.mvvm.data.network.`interface`.BusinessInterface
import ni.devotion.mvvm.model.BusinessList
import ni.devotion.mvvm.model.Categories

interface BusinessRepository {
    suspend fun getBusiness(): List<BusinessList>
}

class  BusinessRepositoryImpl(private val businessService: BusinessInterface):
    BusinessRepository{
    override suspend fun getBusiness(): List<BusinessList> {
        return businessService.requestBusinessList()
    }


}