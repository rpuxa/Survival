package ru.rpuxa.survival.dagger

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.rpuxa.survival.model.database.DataBase
import ru.rpuxa.survival.model.logic.Location
import ru.rpuxa.survival.model.logic.locations.LakeLocation
import ru.rpuxa.survival.view.App
import javax.inject.Singleton

@Module
class Provider(private val app: App) {

    @Provides
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideDataBase(context: Context) =
        Room.databaseBuilder(context, DataBase::class.java, "database.db")
            .build()


    @Provides
    fun playersDao(dataBase: DataBase) = dataBase.playersDao

    @Provides
    fun settingsDao(dataBase: DataBase) = dataBase.settingsDao

    @Provides
    @Singleton
    fun allLocations(context: Context): MutableList<Location> = mutableListOf(
        LakeLocation(context)
    )
}