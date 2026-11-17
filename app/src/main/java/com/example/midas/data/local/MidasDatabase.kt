package com.example.midas.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.midas.data.model.Category
import com.example.midas.data.model.Goal
import com.example.midas.data.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.time.Instant

@Database(entities = [Transaction:: class, Category::class, Goal::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MidasDatabase : RoomDatabase(){
    abstract fun midasDao(): MidasDao
    companion object{
        @Volatile
        private var INSTANCE: MidasDatabase? = null
        fun getDatabase(context: Context): MidasDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, MidasDatabase::class.java,"midas_database"
                ).addCallback(MidasDatabaseCallback()).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class MidasDatabaseCallback : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    val dao = database.midasDao()
                    dao.insertCategory(Category(name = "Храна", isIncome = false, colorHex = "#FF5722"))
                    dao.insertCategory(Category(name = "Транспорт", isIncome = false, colorHex = "#2196F3"))
                    dao.insertCategory(Category(name = "Сметки", isIncome = false, colorHex = "#F44336"))
                    dao.insertCategory(Category(name = "Забавление", isIncome = false, colorHex = "#9C27B0"))

                    dao.insertCategory(Category(name = "Заплата", isIncome = true, colorHex = "#4CAF50"))
                    dao.insertCategory(Category(name = "Подарък", isIncome = true, colorHex = "#8BC34A"))

                }
            }
        }
    }
}