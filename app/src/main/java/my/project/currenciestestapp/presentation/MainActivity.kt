package my.project.currenciestestapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import my.project.currenciestestapp.R
import my.project.currenciestestapp.databinding.ActivityMainBinding
import androidx.appcompat.widget.Toolbar


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    private val viewModel: SharedViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
//    private lateinit var appBarConfiguration: AppBarConfiguration

    val TAG = "aboba"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Показать loader - прогресс бар, пока загружаются данные
        //В этот грузим данные из сети, получаем Rates
        /*Загрузить в room, в бэкграунде через IO coroutines или сразу положить в ROOM базу,
        сразу и показывать из смапленной модельки.
        */
        navController = findNavController(R.id.navHostFragment)
//        setupActionBarWithNavController(navController)
        binding.bottomNavigationView.setupWithNavController(navController)

//        val appBarConfiguration = AppBarConfiguration(setOf(R.id.currencyListFragment, R.id.favoritesFragment))


//        setupActionBarWithNavController(navController, appBarConfiguration)



        }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}