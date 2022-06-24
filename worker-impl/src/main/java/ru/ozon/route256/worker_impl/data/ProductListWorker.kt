package ru.ozon.route256.worker_impl.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_storage_api.CacheApi
import ru.ozon.route256.core_utils.NetUtil
import ru.ozon.route256.core_utils.requireValue

class ProductListWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val cacheApi: CacheApi,
    private val productApi: ProductApi
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            val f = async(Dispatchers.IO) {
                val forceRefresh = inputData.getBoolean("anyway", false)
                if (cacheApi.getCacheProductList() == null || forceRefresh) {
                    val responseList = NetUtil.get {
                        productApi.getProductsInList()
                    }
                    cacheApi.updateCacheProductList(responseList.requireValue().map {
                        it.toEntity()
                    })
                }
            }

            f.await()

            Result.success()
        } catch (exception: Exception) {
            Result.failure()
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(appContext: Context, params: WorkerParameters): ProductListWorker
    }
}