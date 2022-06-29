package ru.ozon.route256.core_navigation_impl

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ozon.route256.core_navigation_impl.databinding.ActivityMainBinding
import ru.ozon.route256.core_navigation_impl.di.FeatureInjectorProxy
import ru.ozon.route256.core_utils.InternetConnectionLiveData
import ru.ozon.route256.core_utils.fadeVisibility

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private lateinit var navController: NavController

    private val connectionLiveData: InternetConnectionLiveData by lazy {
        InternetConnectionLiveData(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        FeatureInjectorProxy.initFeatureProductsDI(this.application)

        super.onCreate(savedInstanceState)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController

        connectionLiveData.observe(this) { isConnected ->
            if (isConnected) {
                binding.internetConnectionInfo.fadeVisibility(INVISIBLE)
            } else {
                binding.internetConnectionInfo.fadeVisibility(VISIBLE)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}