package my.project.currenciestestapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.project.currenciestestapp.data.models.roomDataBase.AppDatabase
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyDao
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesDao
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
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesCurrencyDao(database: AppDatabase): CurrencyDao {
        return database.getCurrencyDao()
    }
    @Singleton
    @Provides
    fun providesFavoritesDao(database: AppDatabase): FavoritesDao {
        return database.getFavoritesDao()
    }
}