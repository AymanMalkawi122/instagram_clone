package com.example.instagram_clone


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.instagram_clone.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController
    private val manager = supportFragmentManager
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragmentContainer)
        actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity, binding.drawerLayout , R.string.nav_open, R.string.nav_close)

        setupBottomNavBar()
        setupDrawerLayout()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupBottomNavBar(){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)
        val unauthorizedFragments = listOf(R.id.globalFragment,R.id.loginFragment,R.id.registerFragment)


        bottomNavigationView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id in unauthorizedFragments) {

                bottomNavigationView.visibility = View.GONE
            } else {

                bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }


    private fun setupDrawerLayout(){
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navDrawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.logout-> {
                    viewModel.logout()
                    navigateToGlobalSection()
                }

                else -> {
                    TODO()}
            }
            true
        }
    }

    private fun navigateToGlobalSection() {
//        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//        val transaction = manager.beginTransaction()
//        val targetFragment = GlobalFragment()
//        transaction.add(R.id.fragmentContainer, targetFragment)
//        transaction.commit()

        val intent = getIntent()
        finish()
        startActivity(intent)
    }
}
