package com.inmy.products.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.inmy.products.*
import com.inmy.products.data.model.Resources
import com.inmy.products.databinding.ActivtyHomeBinding

import com.inmy.products.ui.cart.CartActivity

import kotlinx.android.synthetic.main.app_bar_main.*


class HomeActivty : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var textCartItemCount: TextView
    private var mCartItemCount = 0

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivtyHomeBinding =
            DataBindingUtil.setContentView(this,R.layout.activty_home)
        binding.lifecycleOwner = this

        homeViewModel =
            this.run { ViewModelProvider(this).get(HomeViewModel::class.java) }

        binding.homeViewModel = homeViewModel

        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_order_status,
                R.id.nav_address,
                R.id.nav_feedback,
                R.id.nav_contact_us
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_activty, menu)

        val menuItem: MenuItem = menu.findItem(R.id.action_cart)
        val actionView: View = menuItem.actionView

        textCartItemCount = actionView.findViewById(R.id.cart_badge) as TextView

        mCartItemCount = homeViewModel.checkValuesFromPreference(this,PREFERENCE_KEY_CART_TOTAL)

        homeViewModel.mcartValue?.observe(this, Observer {

            textCartItemCount.text = it.toString()
        })

        setupBadge()

        actionView.setOnClickListener { onOptionsItemSelected(menuItem) }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
           R.id.action_cart -> {
               val intent = Intent(this, CartActivity::class.java)
               startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupBadge() {

        homeViewModel.cartResponseModelListLiveData?.observe(this, {
            when (it.status) {
                Resources.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()){
                        mCartItemCount = homeViewModel.getPreviousSavedCartValue(it.data)
                        textCartItemCount.text = mCartItemCount.toString()

                    }
                }
                Resources.Status.FAILURE ->{
                    textCartItemCount.text = mCartItemCount.toString()
                }
                Resources.Status.LOADING ->{
                    textCartItemCount.text = mCartItemCount.toString()
                }

            }
        })

//        if (mCartItemCount == 0) {
//            if (textCartItemCount.visibility != View.GONE) {
//                textCartItemCount.visibility = View.GONE
//            }
//        } else {
//            textCartItemCount.text = String.valueOf(Math.min(mCartItemCount, 99))
//            if (textCartItemCount.visibility != View.VISIBLE) {
//                textCartItemCount.visibility = View.VISIBLE
//            }
//        }
    }
}