package ni.devotion.mvvm.data.network.headerInterceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    companion object ApiConstants{
        const val BUSINESS = "/api/v1/negocio"
        const val CATEGORIES = "/categoria"
    }

    private lateinit var requestBuilder: Request.Builder

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        requestBuilder = request.newBuilder()
        when (request.url().url().path) {
            BUSINESS -> {
                requestBuilder
                    .addHeader("Content-Type", "application/json")
            }
            CATEGORIES -> {
                requestBuilder
                    .addHeader("Content-Type", "application/json")
            }
            else -> {
                requestBuilder
                    .addHeader("Content-Type", "application/json")
            }
        }
        request = requestBuilder.build()
        return chain.proceed(request)
    }
}
