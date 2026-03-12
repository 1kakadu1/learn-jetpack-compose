package com.example.entertainhub.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.entertainhub.data.local.dao.MovieDao
import com.example.entertainhub.data.local.entity.MovieEntity
import com.example.entertainhub.data.local.entity.MovieListType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toList(data: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, type)
    }
}

class MovieTypeConverter {
    @TypeConverter
    fun fromType(type: MovieListType): String {
        return type.name
    }

    @TypeConverter
    fun toType(value: String): MovieListType {
        return MovieListType.valueOf(value)
    }
}

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class, MovieTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "entertainhub_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}