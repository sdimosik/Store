package ru.ozon.route256.core_navigation_impl

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.ozon.route256.core_navigation_impl.databinding.ActivityMainBinding
import ru.ozon.route256.core_navigation_impl.di.CoreNavigationComponent
import ru.ozon.route256.core_navigation_impl.di.FeatureInjectorProxy
import ru.ozon.route256.core_utils.fadeVisibility
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private lateinit var navController: NavController


    @Inject
    lateinit var connectionTracker: InternetConnectionTracker

    override fun onCreate(savedInstanceState: Bundle?) {
        CoreNavigationComponent.get(application)?.inject(this)
        FeatureInjectorProxy.initFeatureProductsDI(application)

        super.onCreate(savedInstanceState)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController

        lifecycleScope.launch {
            connectionTracker.statusFlow.flowWithLifecycle(
                this@MainActivity.lifecycle,
                Lifecycle.State.STARTED
            )
                .collectLatest { isConnected ->
                    if (isConnected) {
                        binding.internetConnectionInfo.fadeVisibility(INVISIBLE)
                    } else {
                        binding.internetConnectionInfo.fadeVisibility(VISIBLE)
                    }
                }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}