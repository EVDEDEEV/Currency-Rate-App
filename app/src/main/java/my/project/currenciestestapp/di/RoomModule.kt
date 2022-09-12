package my.project.currenciestestapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.project.currenciestestapp.data.models.room.AppDatabase
import my.project.currenciestestapp.data.models.room.RateDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesRateDao(database: AppDatabase): RateDao {
        return database.getRateDao()
    }
}