package dizzcode.com.inventoryapp.data

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(
    private val itemDao : ItemDao
) : ItemsRepository {

    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id = id)

    override suspend fun insertItem(item: Item) = itemDao.insert(item = item)

    override suspend fun deleteItem(item: Item) = itemDao.delete(item = item)

    override suspend fun updateItem(item: Item) = itemDao.update(item = item)
}
