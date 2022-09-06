package my.project.currenciestestapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import my.project.currenciestestapp.R
import my.project.currenciestestapp.data.api.ApiConstants.BASE_URL

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    val TAG = "aboba"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHostFragment)

        setupActionBarWithNavController(navController)



        Log.d(TAG, BASE_URL)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}