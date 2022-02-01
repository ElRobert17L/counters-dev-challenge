package mx.com.rlr.countersdevchallenge.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import mx.com.rlr.countersdevchallenge.R
import mx.com.rlr.countersdevchallenge.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController

    private lateinit var navGraph: NavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        navController = Navigation.findNavController(this, R.id.home_nav_host)
        navGraph = navController.navInflater.inflate(R.navigation.nav_graph_home)
        navController.setGraph(navGraph, intent.extras)
    }

}