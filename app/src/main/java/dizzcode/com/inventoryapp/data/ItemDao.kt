package dizzcode.com.inventoryapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from items " +
            "ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

}

/**
 * ----------
 * Note 01:
 * ----------
 *  onConflict = OnConflictStrategy.IGNORE
 *
 *  The argument onConflict tells the Room what to do in case of a conflict.
 *  The OnConflictStrategy.IGNORE strategy ignores a new item.
 *
 * ----------
 * Note 02:
 * ----------
 * @Delete
 *
 * The @Delete annotation deletes an item or a list of items. You need to pass the entities you want to delete.
 * If you don't have the entity, you might have to fetch it before calling the delete() function.
 *
 * ----------
 * Note 03:
 * ----------
 * Flow
 *
 * Flow in Room database can keep the data up-to-date by emitting a notification whenever
 * the data in the database changes. This allows you to observe the data and update your UI accordingly.
 *
 */
