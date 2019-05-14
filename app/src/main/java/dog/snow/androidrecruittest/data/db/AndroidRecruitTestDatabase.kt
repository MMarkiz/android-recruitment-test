package dog.snow.androidrecruittest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * author marcinm on 2019-05-14.
 */
@Database(entities = [Item::class], version = 1)
abstract class AndroidRecruitTestDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var instance: AndroidRecruitTestDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AndroidRecruitTestDatabase::class.java,
            "androidrecruittest.db"
        ).build()
    }
}