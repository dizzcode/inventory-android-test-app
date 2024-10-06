package dizzcode.com.inventoryapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    InventoryDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}

/**
 * ----------
 * Note 01:
 * ----------
 *  Instance variable
 *
 *  The Instance variable keeps a reference to the database, when one has been created. This helps maintain
 *  a single instance of the database opened at a given time, which is an expensive resource to create and maintain.
 *
 * ----------
 * Note 02:
 * ----------
 * @Volatile
 *
 * The value of a volatile variable is never cached, and all reads and writes are to and from the main memory.
 * These features help ensure the value of Instance is always up to date and is the same for all execution threads.
 * It means that changes made by one thread to Instance are immediately visible to all other threads.
 *
 * ----------
 * Note 03:
 * ----------
 * Race condition
 *
 * Multiple threads can potentially ask for a database instance at the same time, which results
 * in two databases instead of one. This issue is known as a race condition.
 *
 * synchronized
 *
 * Wrapping the code to get the database inside a synchronized block means that only one thread of
 * execution at a time can enter this block of code, which makes sure the database only gets initialized once.
 * Use synchronized{} block to avoid the race condition.
 *
 * ----------
 * Note 04:
 * ----------
 *  fallbackToDestructiveMigration
 *
 *  Allows Room to destructively recreate database tables if Migrations that would migrate old
 *  database schemas to the latest schema version are not found.
 *
 *** Normally, you would provide a migration object with a migration strategy for when the schema changes.
 *  A migration object is an object that defines how you take all rows with the old schema and convert
 *  them to rows in the new schema, so that no data is lost.
 *
 *
 *
 */
