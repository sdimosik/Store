package ru.ozon.route256.core_utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ozon.route256.core_utils.di.PerFeature
import javax.inject.Inject
import javax.inject.Provider

@PerFeature
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull()?.value
        ?: throw IllegalArgumentException("unknown model class $modelClass")
        return creator.get() as T
    }
}