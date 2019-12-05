package com.example.myapplication

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.navigation.findNavController
import com.example.myapplication.kmb.eta.Response
import com.example.myapplication.model.AppDatabase
import com.example.myapplication.model.Route
import com.example.myapplication.ui.routes.RoutesFragmentDirections
import com.example.myapplication.ui.routes.RoutesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val db = AppDatabase.get(applicationContext)
        MainScope().launch {
            fetchRoutes()
        }


        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_routes, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        KMBService.instance.getETA("1").enqueue(object:
            Callback<Response> {
            override fun onFailure(
                call: Call<Response>,
                t: Throwable
            ) {
                Log.e("MainActivity", t.message)
            }


            override fun onResponse(
                call: Call<com.example.myapplication.kmb.eta.Response>,
                response: retrofit2.Response<Response>
            ) {
                Log.d("MainActivity", response.toString())

                if (response.isSuccessful) {
                    response.body()?.let {
                        for (eta in it.response) {
                            Log.d("MainActivity", eta.t)
                        }
                    }
                }
            }
        })

//        findNavController()
    }
    suspend fun fetchRoutes() = withContext(Dispatchers.IO) {
        Route.fetchAll()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun onClickRoute(vm:RoutesViewModel) {
        vm.title.value?.let {
            findNavController(R.id.nav_host_fragment).navigate(RoutesFragmentDirections.actionNavRoutesToNavRouteDetail(it))
        }
    }
}
