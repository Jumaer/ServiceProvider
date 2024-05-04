package com.friendship.bhaibhaiclinic

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.friendship.bhaibhaiclinic.databinding.ActivityMainBinding
import com.friendship.bhaibhaiclinic.view_model.ProviderViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.apply { ->
            statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.theme_color)
            WindowCompat.getInsetsController(this, decorView).isAppearanceLightStatusBars = false
        }
        setNav()
    }


    //-------------------------------- SET NAVIGATION ------------------------------------//

    private fun setNav() {
        val navGraph: NavGraph
        val navController: NavController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navGraph.apply {
            setStartDestination(R.id.tabContainerFragment)
        }
        navController.setGraph(navGraph, intent.extras)
    }
}