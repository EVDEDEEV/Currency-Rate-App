package my.project.currenciestestapp.di

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import my.project.currenciestestapp.R

//@Module
//@InstallIn(ActivityComponent::class)
//object MainActivityModule {
//
//    @Provides
//    fun provideNavController(activity: Activity): NavController {
//        return activity.findNavController(R.id.navHostFragment)
//    }
//}