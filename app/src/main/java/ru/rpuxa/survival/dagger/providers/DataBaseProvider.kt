package ru.rpuxa.survival.dagger.providers

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.rpuxa.survival.model.database.DataBase
import javax.inject.Singleton

@Module
class DataBaseProvider {

    @Provides
    @Singleton
    fun provideDataBase(context: Context) =
        Room.databaseBuilder(context, DataBase::class.java, "database.db")
            .build()


    @Provides
    fun playersDao(dataBase: DataBase) = dataBase.playersDao

    @Provides
    fun settingsDao(dataBase: DataBase) = dataBase.settingsDao
}