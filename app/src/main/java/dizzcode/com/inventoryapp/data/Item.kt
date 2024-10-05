package dizzcode.com.inventoryapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entity data class represents a single row in the database.
 */

@Entity(tableName = "items")
class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)

/**
 *  @PrimaryKey(autoGenerate = true)
 *  If autoGenerate is set to true, Room will automatically generate a unique value
 *  for the primary key column when a new entity instance is inserted into the database.
 */
