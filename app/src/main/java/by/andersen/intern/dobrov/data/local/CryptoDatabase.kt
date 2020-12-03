package by.andersen.intern.dobrov.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData

@Database(entities = [CryptoData::class], version = 1, exportSchema = false)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun cryptoDAO(): CryptoDAO

    companion object {
        private const val DB_NAME = "cryptodata.db"

        private lateinit var cryptoDatabase: CryptoDatabase

        @JvmStatic
        fun getInstance(context: Context): CryptoDatabase {
            cryptoDatabase = Room.databaseBuilder(context, CryptoDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

            return cryptoDatabase
        }
    }
}