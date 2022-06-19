package ru.ozon.route256.core_navigation_impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.ozon.route256.core_navigation_impl.di.FeatureInjectorProxy
import ru.ozon.route256.feature_pdp_impl.presentation.view.PDPFragment
import ru.ozon.route256.feature_products_impl.presentation.view.ProductsFragment

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

//        val inflater = navHost.navController.navInflater
//        val graph = inflater.inflate(R.navigation.mobile_navigation)
//        graph.setStartDestination(R.id.fragment_products)
        navController = navHost.navController
        //navController.setGraph(graph, intent.extras)
    }

    fun navigateProduct() {
        FeatureInjectorProxy.initFeatureProductsDI()
        val newFragment = ProductsFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, newFragment, ProductsFragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}