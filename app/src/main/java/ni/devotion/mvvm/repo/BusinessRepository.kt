package ni.devotion.mvvm.repo

import ni.devotion.mvvm.data.network.`interface`.BusinessInterface
import ni.devotion.mvvm.model.Business

interface BusinessRepository {
    suspend fun getBusiness(): List<Business>
}

class  BusinessRepositoryImpl(private val businessService: BusinessInterface):
    BusinessRepository{
    override suspend fun getBusiness(): List<Business> {
        return businessService.requestBusiness()
    }

}