package dog.snow.androidrecruittest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * author marcinm on 2019-05-14.
 */
@Dao
interface ItemDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertItemsList(itemEntry: ArrayList<Item>)

	@Query("select * from item")
	fun getItems(): LiveData<List<Item>>

	@Query("delete from item")
	fun clearItems()
}