package dizzcode.com.inventoryapp

import android.app.Application
import dizzcode.com.inventoryapp.data.AppContainer
import dizzcode.com.inventoryapp.data.AppDataContainer

class InventoryApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
