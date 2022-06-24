package ru.ozon.route256.core_navigation_impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.ozon.route256.core_navigation_impl.di.FeatureInjectorProxy

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    init {
        FeatureInjectorProxy.initFeatureProductsDI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FeatureInjectorProxy.initFeatureProductsDI()

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}