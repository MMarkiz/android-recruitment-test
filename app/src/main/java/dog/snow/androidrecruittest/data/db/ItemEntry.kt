package dog.snow.androidrecruittest.data.db

import androidx.room.ColumnInfo

/**
 * author marcinm on 2019-05-14.
 */
data class ItemEntry(
	@ColumnInfo(name = "description")
	val description: String,
	@ColumnInfo(name = "icon")
	val icon: String,
	@ColumnInfo(name = "id")
	val id: Int,
	@ColumnInfo(name = "name")
	val name: String,
	@ColumnInfo(name = "timestamp")
	val timestamp: Long,
	@ColumnInfo(name = "url")
	val url: String
)