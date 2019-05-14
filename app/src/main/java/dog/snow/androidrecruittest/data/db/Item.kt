package dog.snow.androidrecruittest.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author marcinm on 2019-05-14.
 */
@Entity(tableName = "item")
data class Item(
    @PrimaryKey
    val id: Int,
    val description: String,
    val icon: String,
    val name: String,
    val timestamp: Long,
    val url: String,
    var uri: String
)